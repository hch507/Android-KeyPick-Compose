package com.example.ranking

sealed class RankUiState<out T>(val _data : T?){
    object Loading : RankUiState<Nothing>(_data = null)
    object Error : RankUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : RankUiState<T>(_data = data)
}
