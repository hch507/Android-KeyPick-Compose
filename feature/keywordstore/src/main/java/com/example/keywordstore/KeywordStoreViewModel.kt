package com.example.keywordstore

import com.example.domain.DeleteKeywordUsecase
import com.example.domain.GetStoredKeywordUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class KeywordStoreViewModel @Inject constructor(
    private val deleteKeywordUsecase: DeleteKeywordUsecase,
    private val getStoredKeywordUsecase: GetStoredKeywordUsecase
) {
    private val _keywordSaveState =
        MutableStateFlow<KeywordInfoUiState<Boolean>>(KeywordInfoUiState.Loading)
    val keywordSaveState = _keywordSaveState.asStateFlow()


    fun getStoredKeywords(){

    }

    fun deleteKeyword(keyword : String){

    }
}