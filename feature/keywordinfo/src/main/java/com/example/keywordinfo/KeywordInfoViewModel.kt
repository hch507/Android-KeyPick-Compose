package com.example.keywordinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.FetchKeywordInfoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordInfoViewModel @Inject constructor(
    private val fetchKeywordInfoUsecase: FetchKeywordInfoUsecase
) : ViewModel() {
    private val _monthlySearchResult =
        MutableStateFlow<MonthlySearchUiState<String>>(MonthlySearchUiState.Loading)
    val monthlySearchResult = _monthlySearchResult.asStateFlow()

    fun fetchkeywordInfoData(keyword: String) {
        viewModelScope.launch {
            val result =fetchKeywordInfoUsecase(keyword = keyword).collect { result ->
                Log.d("fetchkeywordInfoData", "결과: $result")
            }


        }
    }


}