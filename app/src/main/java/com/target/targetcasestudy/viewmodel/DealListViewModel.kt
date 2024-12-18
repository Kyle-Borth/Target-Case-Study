package com.target.targetcasestudy.viewmodel

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
class DealListViewModel @Inject constructor(private val dealsRepository: DealsRepository) : ViewModel() {

    val deals get() = dealsRepository.deals

    var isLoading by mutableStateOf(true)
        private set

    init {
        updateDeals()
    }

    fun updateDeals() = viewModelScope.launch {
        isLoading = true
        dealsRepository.updateDeals()
        isLoading = false
    }

}