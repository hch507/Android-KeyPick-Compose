package com.example.search

sealed class SearchUiState<out T>(val _data : T?) {
    object Idle : SearchUiState<Nothing>(_data = null)
    object Loading : SearchUiState<Nothing>(_data = null)
    object Error : SearchUiState<Nothing>(_data = null)
    data class Success<out T>(val data : T) : SearchUiState<T>(_data = data)
}