package com.target.targetcasestudy.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DealApi {

    // Important Note: The "description" returned by this API is a placeholder.
    // Must call retrieveDeal API to get actual description.
    @GET("deals")
    suspend fun retrieveDeals(): Response<DealResponse>

    @GET("deals/{dealId}")
    suspend fun retrieveDeal(@Path("dealId") dealId: Int): Response<Product>

}