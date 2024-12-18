package com.target.targetcasestudy.api

import android.util.Log
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class DealsNetworkServiceTest {

    private val retrofitBuilder = mockk<Retrofit.Builder>()
    private val retrofit = mockk<Retrofit>()
    private val dealApi = mockk<DealApi>()

    private val service = DealsNetworkService()

    init {
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 1

        mockkConstructor(Retrofit.Builder::class)
        every { anyConstructed<Retrofit.Builder>().baseUrl(any<String>()) } returns retrofitBuilder

        every { retrofitBuilder.client(any()) } returns retrofitBuilder
        every { retrofitBuilder.addConverterFactory(any()) } returns retrofitBuilder
        every { retrofitBuilder.build() } returns retrofit
        every { retrofit.create(DealApi::class.java) } returns dealApi
    }

    @Test
    fun `retrieveDeals calls API`() = runTest {
        val mockedResponse = mockk<Response<DealResponse>>()
        val mockedDealResponse = mockk<DealResponse>()

        coEvery { dealApi.retrieveDeals() } returns mockedResponse
        every { mockedResponse.isSuccessful } returns true
        every { mockedResponse.body() } returns mockedDealResponse
        every { mockedResponse.code() } returns 200

        val response = service.retrieveDeals()

        coVerify(exactly = 1) { dealApi.retrieveDeals() }
        Assert.assertEquals(WrappedResponse.SuccessWithBody(mockedDealResponse), response)
    }

    @Test
    fun `retrieveDealDetails calls API`() = runTest {
        val dealId = 22
        val mockedResponse = mockk<Response<Product>>()
        val mockedProduct = mockk<Product>()

        coEvery { dealApi.retrieveDeal(dealId) } returns mockedResponse
        every { mockedResponse.isSuccessful } returns true
        every { mockedResponse.body() } returns mockedProduct
        every { mockedResponse.code() } returns 200

        val response = service.retrieveDealDetails(dealId)

        coVerify(exactly = 1) { dealApi.retrieveDeal(dealId) }
        Assert.assertEquals(WrappedResponse.SuccessWithBody(mockedProduct), response)
    }

    @Test
    fun `empty body returns SuccessNoBody`() = runTest {
        val mockedResponse = mockk<Response<DealResponse>>()

        coEvery { dealApi.retrieveDeals() } returns mockedResponse
        every { mockedResponse.isSuccessful } returns true
        every { mockedResponse.body() } returns null
        every { mockedResponse.code() } returns 204

        val response = service.retrieveDeals()

        Assert.assertEquals(WrappedResponse.SuccessNoBody<DealResponse>(204), response)
    }

    @Test
    fun `exception causes LocalError`() = runTest {
        val exception = IOException("Unit Test Exception")

        coEvery { dealApi.retrieveDeals() } throws exception

        val response = service.retrieveDeals()

        Assert.assertEquals(WrappedResponse.LocalError<DealResponse>(exception), response)
    }

    @Test
    fun `network error causes NetworkError`() = runTest {
        val mockedResponse = mockk<Response<DealResponse>>()
        val mockedBody = mockk<ResponseBody>()

        coEvery { dealApi.retrieveDeals() } returns mockedResponse
        every { mockedResponse.isSuccessful } returns false
        every { mockedResponse.code() } returns 404
        every { mockedResponse.errorBody() } returns mockedBody

        val response = service.retrieveDeals()

        val expected = WrappedResponse.NetworkError<DealResponse>(mockedBody, null, 404)
        Assert.assertEquals(expected, response)
    }

}