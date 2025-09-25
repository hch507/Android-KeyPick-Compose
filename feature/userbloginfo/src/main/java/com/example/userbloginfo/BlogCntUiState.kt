package com.example.userbloginfo

sealed class BlogCntUiState<out T>(val _data : T?){
    object Loading : BlogCntUiState<Nothing>(_data = null)
    object Error : BlogCntUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : BlogCntUiState<T>(_data = data)
}
