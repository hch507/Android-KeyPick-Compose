package com.example.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CheckLoginedUsecase
import com.example.domain.FetchLoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchLoginUsecase: FetchLoginUsecase,
    private val checkLoginedUsecase: CheckLoginedUsecase
) : ViewModel(){
    private val _blogIdResult = MutableStateFlow<LoginUiState<Boolean>>(LoginUiState.Loading)
    val blogIdResult = _blogIdResult.asStateFlow()

    val autologinState: StateFlow<AutoLoginStatus> = checkLoginedUsecase.invoke()
        .map { blogId ->
            if (!blogId.isNullOrEmpty()) AutoLoginStatus.LOGGED_IN
            else AutoLoginStatus.NOT_LOGGED_IN
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AutoLoginStatus.LOADING
        )

    fun checkBlogIdExists(userId : String){
        viewModelScope.launch {
            fetchLoginUsecase.invoke(userId)
                .onStart { _blogIdResult.update { LoginUiState.Loading }  }
                .catch { _blogIdResult.update { LoginUiState.Error } }
                .collectLatest { value -> _blogIdResult.value = LoginUiState.Success(value)}
        }
    }


}