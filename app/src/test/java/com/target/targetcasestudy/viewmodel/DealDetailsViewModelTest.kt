package com.target.targetcasestudy.viewmodel

import com.target.targetcasestudy.MainDispatcherRule
import com.target.targetcasestudy.api.WrappedResponse
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.repository.DealsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DealDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dealsRepository = mockk<DealsRepository>()

    private val viewModel = DealDetailsViewModel(dealsRepository = dealsRepository)

    @Test
    fun `Initial State`() {
        Assert.assertNull(viewModel.dealDetails)
        Assert.assertTrue(viewModel.isLoading)
    }

    @Test
    fun `setDeal binds to repository`() {
        val dealId = 21
        val dealItemMock = mockk<DealItem>()

        coEvery { dealsRepository.updateProductDetails(dealId) } returns WrappedResponse.SuccessWithBody(mockk())
        every { dealsRepository.dealDetails } returns mapOf(dealId to dealItemMock)

        viewModel.setDeal(dealId)

        Assert.assertEquals(dealItemMock, viewModel.dealDetails)
    }

    @Test
    fun `setDeal makes update request`() = runTest {
        val dealId = 22

        coEvery { dealsRepository.updateProductDetails(dealId) } returns WrappedResponse.SuccessWithBody(mockk())

        viewModel.setDeal(dealId)

        coVerify { dealsRepository.updateProductDetails(dealId) }
        Assert.assertFalse(viewModel.isLoading)
    }

}