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

class DealListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dealsRepository = mockk<DealsRepository>()

    private val viewModel: DealListViewModel by lazy { DealListViewModel(dealsRepository = dealsRepository) }

    @Test
    fun `Initial State`() = runTest {
        val dealsMocked = mockk<List<DealItem>>()

        every { dealsRepository.deals } returns dealsMocked
        coEvery { dealsRepository.updateDeals() } returns WrappedResponse.SuccessNoBody()

        Assert.assertEquals(dealsMocked, viewModel.deals)
        Assert.assertFalse(viewModel.isLoading)
        coVerify(exactly = 1) { dealsRepository.updateDeals() }
    }

}