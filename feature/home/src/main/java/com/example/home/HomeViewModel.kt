package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CheckLoginedUsecase
import com.example.home.navigation.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkLoginedUsecase: CheckLoginedUsecase
):ViewModel() {

    val blogIdState: StateFlow<HomeUiState<String>> = checkLoginedUsecase.invoke()
        .map { blogId ->
            if (!blogId.isNullOrEmpty()) HomeUiState.Success(blogId)
            else HomeUiState.Error
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )
}