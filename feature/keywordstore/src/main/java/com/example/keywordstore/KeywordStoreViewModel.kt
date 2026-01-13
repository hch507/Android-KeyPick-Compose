package com.example.keywordstore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.DeleteKeywordUsecase
import com.example.domain.GetStoredKeywordUsecase
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
class KeywordStoreViewModel @Inject constructor(
    private val deleteKeywordUsecase: DeleteKeywordUsecase,
    private val getStoredKeywordUsecase: GetStoredKeywordUsecase
) :ViewModel(){
    private val _keywordSaveState =
        MutableStateFlow<KeywordStoreUiState<List<String>>>(KeywordStoreUiState.Loading)
    val keywordSaveState = _keywordSaveState.asStateFlow()


    fun getStoredKeywords(){
        viewModelScope.launch {
            getStoredKeywordUsecase.invoke()
                .onStart { _keywordSaveState.update { KeywordStoreUiState.Loading } }
                .catch { _keywordSaveState.update { KeywordStoreUiState.Error } }
                .collectLatest { value ->
                    _keywordSaveState.value = KeywordStoreUiState.Success(value)
                }
        }
    }

    fun deleteKeyword(keyword : String){

    }
}