package com.example.keywordinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordInfoViewModel @Inject constructor(
    private val keywordRepository: KeywordRepository
) : ViewModel() {
    private val _monthlySearchResult =
        MutableStateFlow<MonthlySearchUiState<String>>(MonthlySearchUiState.Loading)
    val monthlySearchResult = _monthlySearchResult.asStateFlow()

    fun fetchMonthlySearchData(keyword: String) {
        viewModelScope.launch {
            keywordRepository.fetchMonthlySearch(keyword = keyword)
        }
    }

    fun fetchBlogPostCountAndTrendData(keyword: String) {
        viewModelScope.launch {
            keywordRepository.fetchBlogPostCountAndTrend(keyword = keyword)
        }
    }

    fun fetchKeywordRelData(keyword: String) {
        viewModelScope.launch {
            keywordRepository.fetchKeywordRel(keyword = keyword)
        }
    }
}