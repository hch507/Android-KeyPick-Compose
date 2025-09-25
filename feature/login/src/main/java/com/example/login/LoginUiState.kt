package com.example.login

sealed class LoginUiState<out T>(val _data : T?){
    object Loading : LoginUiState<Nothing>(_data = null)
    object Error : LoginUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : LoginUiState<T>(_data = data)
}
