package com.target.targetcasestudy.repository

import android.util.Log
import com.target.targetcasestudy.Generators
import com.target.targetcasestudy.api.DealResponse
import com.target.targetcasestudy.api.DealsNetworkService
import com.target.targetcasestudy.api.Product
import com.target.targetcasestudy.api.WrappedResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DealsRepositoryTest {

    private val dealsService = mockk<DealsNetworkService>()

    private val repository = DealsRepository(dealsService = dealsService)

    init {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 1
        every { Log.e(any(), any(), any()) } returns 1
    }

    @Test
    fun `Initial state`() {
        Assert.assertEquals(0, repository.deals.size)
        Assert.assertEquals(0, repository.dealDetails.size)
    }

    @Test
    fun `updateDeals SuccessWithBody`() = runTest {
        val products = listOf(
            Generators.product(0, 100, 50),
            Generators.product(1, 200, null),
            Generators.product(2, 300, 250)
        )
        val dealResponse = DealResponse(products = products)
        val expectedResponse = WrappedResponse.SuccessWithBody(dealResponse)

        coEvery { dealsService.retrieveDeals() } returns expectedResponse

        val actualResponse = repository.updateDeals()

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertEquals(2, repository.deals.size)
    }

    @Test
    fun `updateDeals SuccessNoBody`() = runTest {
        val expectedResponse = WrappedResponse.SuccessNoBody<DealResponse>()

        coEvery { dealsService.retrieveDeals() } returns expectedResponse

        val actualResponse = repository.updateDeals()

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertEquals(0, repository.deals.size)
    }

    @Test
    fun `updateDeals NetworkError`() = runTest {
        val expectedResponse = WrappedResponse.NetworkError<DealResponse>(mockk(), mockk(), 404)

        coEvery { dealsService.retrieveDeals() } returns expectedResponse

        val actualResponse = repository.updateDeals()

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertEquals(0, repository.deals.size)
    }

    @Test
    fun `updateDeals LocalError`() = runTest {
        val expectedResponse = WrappedResponse.LocalError<DealResponse>(mockk())

        coEvery { dealsService.retrieveDeals() } returns expectedResponse

        val actualResponse = repository.updateDeals()

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertEquals(0, repository.deals.size)
    }

    @Test
    fun `updateProductDetails SuccessWithBody`() = runTest {
        val dealId = 22
        val product = Generators.product(dealId, 500, 450)
        val expectedResponse = WrappedResponse.SuccessWithBody(product)

        coEvery { dealsService.retrieveDealDetails(dealId) } returns expectedResponse

        val actualResponse = repository.updateProductDetails(dealId)

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertEquals(dealId, repository.dealDetails[dealId]?.id)
    }

    @Test
    fun `updateProductDetails SuccessNoBody`() = runTest {
        val dealId = 23
        val expectedResponse = WrappedResponse.SuccessNoBody<Product>()

        coEvery { dealsService.retrieveDealDetails(dealId) } returns expectedResponse

        val actualResponse = repository.updateProductDetails(dealId)

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertNull(repository.dealDetails[dealId]?.id)
    }

    @Test
    fun `updateProductDetails NetworkError`() = runTest {
        val dealId = 23
        val expectedResponse = WrappedResponse.NetworkError<Product>(mockk(), null, 404)

        coEvery { dealsService.retrieveDealDetails(dealId) } returns expectedResponse

        val actualResponse = repository.updateProductDetails(dealId)

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertNull(repository.dealDetails[dealId]?.id)
    }

    @Test
    fun `updateProductDetails LocalError`() = runTest {
        val dealId = 23
        val expectedResponse = WrappedResponse.LocalError<Product>(mockk())

        coEvery { dealsService.retrieveDealDetails(dealId) } returns expectedResponse

        val actualResponse = repository.updateProductDetails(dealId)

        Assert.assertEquals(expectedResponse, actualResponse)
        Assert.assertNull(repository.dealDetails[dealId]?.id)
    }

}