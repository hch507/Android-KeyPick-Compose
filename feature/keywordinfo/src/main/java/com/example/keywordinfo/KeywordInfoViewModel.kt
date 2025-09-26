package com.example.keywordinfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.FetchKeywordInfoUsecase
import com.keypick.core.model.KeywordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordInfoViewModel @Inject constructor(
    private val fetchKeywordInfoUsecase: FetchKeywordInfoUsecase
) : ViewModel() {
    private val _keywordInfoState =
        MutableStateFlow<KeywordInfoUiState<KeywordInfo>>(KeywordInfoUiState.Loading)
    val keywordInfoState = _keywordInfoState.asStateFlow()

    fun fetchkeywordInfoData(keyword: String) {
        viewModelScope.launch {
            fetchKeywordInfoUsecase(keyword = keyword)
                .onStart { _keywordInfoState.update { KeywordInfoUiState.Loading }  }
                .catch { _keywordInfoState.update { KeywordInfoUiState.Error } }
                .collectLatest { value -> _keywordInfoState.value = KeywordInfoUiState.Success(value) }
        }
    }


}