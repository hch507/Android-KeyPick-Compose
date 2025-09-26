package com.example.userbloginfo

sealed class LogoutState<out T>(val _data : T?){
    object Loading : LogoutState<Nothing>(_data = null)
    object Error : LogoutState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : LogoutState<T>(_data = data)
}
