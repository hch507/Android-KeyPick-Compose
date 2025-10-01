package com.example.home.navigation

sealed class HomeUiState<out T>(val _data : T?){
    object Loading : HomeUiState<Nothing>(_data = null)
    object Error : HomeUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : HomeUiState<T>(_data = data)
}
