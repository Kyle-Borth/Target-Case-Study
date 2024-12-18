package com.target.targetcasestudy.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "DealsServ"
private const val DEALS_BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"

@Singleton
class DealsNetworkService @Inject constructor() {

    //region Variable Declaration

    private val converterFactory by lazy {
        jsonSerializer.asConverterFactory("application/json".toMediaType())
    }

    private val dealApi: DealApi by lazy {
        Retrofit.Builder()
            .baseUrl(DEALS_BASE_URL)
            .client(buildOkHttpClient())
            .addConverterFactory(converterFactory)
            .build()
            .create(DealApi::class.java)
    }

    //endregion

    suspend fun retrieveDeals() = wrapRequest { dealApi.retrieveDeals() }

    suspend fun retrieveDealDetails(dealId: Int) = wrapRequest { dealApi.retrieveDeal(dealId) }

    //region Utility Functions

    private fun buildOkHttpClient(timeoutSeconds: Long = 10L) = OkHttpClient.Builder().apply {
        connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
        readTimeout(timeoutSeconds, TimeUnit.SECONDS)
        writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
    }.build()

    private suspend fun <T> wrapRequest(
        request: suspend () -> Response<T>
    ) : WrappedResponse<T> = withContext(Dispatchers.IO) {
        try {
            val response = request()

            if(response.isSuccessful) {
                response.body()?.let { body ->
                    WrappedResponse.SuccessWithBody(body, response.code())
                } ?: WrappedResponse.SuccessNoBody(response.code())
            }
            else {
                WrappedResponse.NetworkError(response.errorBody(), null, response.code())
            }
        }
        catch (ioE: IOException) {
            // May indicate the user does not have an internet connection
            Log.e(TAG, "API Request Exception", ioE)
            WrappedResponse.LocalError(ioE)
        }
        catch (httpE: HttpException) {
            // Error response from the api
            Log.e(TAG, "API Request Exception: ${httpE.message()}", httpE)
            WrappedResponse.NetworkError(null, httpE, httpE.code())
        }
    }

    //endregion

    companion object {
        val jsonSerializer by lazy {
            Json {
                ignoreUnknownKeys = true
                explicitNulls = false
                coerceInputValues = true
            }
        }
    }

}

// region Wrapped Response

sealed class WrappedResponse<T> {

    data class SuccessWithBody<T>(val body: T, val responseCode: Int = 200) : WrappedResponse<T>()

    data class SuccessNoBody<T>(val responseCode: Int = 204) : WrappedResponse<T>()

    data class LocalError<T>(val throwable: Throwable) : WrappedResponse<T>()

    data class NetworkError<T>(
        val body: ResponseBody?,
        val throwable: Throwable?,
        val responseCode: Int
    ) : WrappedResponse<T>()

}

//endregion
