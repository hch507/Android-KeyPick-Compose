package com.example.keywordstore

sealed class KeywordStoreUiState<out T>(val _data : T?){
    object Loading : KeywordStoreUiState<Nothing>(_data = null)
    object Error : KeywordStoreUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : KeywordStoreUiState<T>(_data = data)
}
