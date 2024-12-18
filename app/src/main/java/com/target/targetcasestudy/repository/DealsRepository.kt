package com.target.targetcasestudy.repository

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.target.targetcasestudy.api.DealsNetworkService
import com.target.targetcasestudy.api.WrappedResponse
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.toDealItem
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "DealsRepo"

@Singleton
class DealsRepository @Inject constructor(private val dealsService: DealsNetworkService) {

    var deals by mutableStateOf<List<DealItem>>(emptyList())
        private set

    private val _dealDetails = mutableStateMapOf<Int, DealItem>()
    val dealDetails by derivedStateOf { _dealDetails.toMap() }

    suspend fun updateDeals() = dealsService.retrieveDeals().also { response ->
        response.requireResponseBody(onSuccess = { deals = it.deals }, onFailureLogMessage = "Error Updating Deals")
    }

    suspend fun updateProductDetails(dealId: Int) = dealsService.retrieveDealDetails(dealId).also { response ->
        response.requireResponseBody(
            onSuccess = { product -> product.toDealItem()?.let { _dealDetails[dealId] = it } },
            onFailureLogMessage = "Error Updating Deal Details"
        )
    }

    private fun <T> WrappedResponse<T>.requireResponseBody(onSuccess: (T) -> Unit, onFailureLogMessage: String) {
        when(this) {
            is WrappedResponse.SuccessWithBody -> onSuccess(this.body)
            is WrappedResponse.SuccessNoBody -> Log.e(TAG, "$onFailureLogMessage: No Response Body")
            is WrappedResponse.NetworkError -> {
                Log.e(TAG, "$onFailureLogMessage: Network Error ${this.responseCode}", this.throwable)
            }
            is WrappedResponse.LocalError -> Log.e(TAG, "$onFailureLogMessage: Local Error", this.throwable)
        }
    }

}