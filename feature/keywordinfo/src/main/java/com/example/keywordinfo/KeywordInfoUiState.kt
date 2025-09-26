package com.example.keywordinfo

sealed class KeywordInfoUiState<out T>(val _data : T?){
    object Loading : KeywordInfoUiState<Nothing>(_data = null)
    object Error : KeywordInfoUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : KeywordInfoUiState<T>(_data = data)
}
