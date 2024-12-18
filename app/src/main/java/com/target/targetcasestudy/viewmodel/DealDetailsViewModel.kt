package com.target.targetcasestudy.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.repository.DealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealDetailsViewModel @Inject constructor(private val dealsRepository: DealsRepository) : ViewModel() {

    private var dealId by mutableStateOf<Int?>(null)

    val dealDetails by derivedStateOf { dealId?.let { dealsRepository.dealDetails[it] } }

    var isLoading by mutableStateOf(true)
        private set

    fun setDeal(id: Int) {
        dealId = id

        updateDealDetails(id)
    }

    private fun updateDealDetails(id: Int) = viewModelScope.launch {
        isLoading = true
        dealsRepository.updateProductDetails(id)
        isLoading = false
    }

}