package com.example.userbloginfo

sealed class RecommendUiState<out T>(val _data : T?){
    object Loading : RecommendUiState<Nothing>(_data = null)
    object Error : RecommendUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : RecommendUiState<T>(_data = data)
}
