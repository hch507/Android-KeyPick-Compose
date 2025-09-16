package com.example.keywordinfo

sealed class MonthlySearchUiState<out T>(val _data : T?){
    object Loading : MonthlySearchUiState<Nothing>(_data = null)
    object Error : MonthlySearchUiState<Nothing>(_data = null)
    data class success<out T>(val data : T) : MonthlySearchUiState<T>(_data = data)
}
