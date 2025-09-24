package com.example.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.LoginOrCntRepository
import com.example.domain.FetchLoginOrCntUsecase
import com.keypick.core.model.UserBlogCntData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchLoginOrCntUsecase: FetchLoginOrCntUsecase
) : ViewModel(){
    private val _blogIdOrCntResult = MutableStateFlow<LoginUiState<UserBlogCntData>>(LoginUiState.Loading)
    val blogIdOrCntResult = _blogIdOrCntResult.asStateFlow()
    var blogId by mutableStateOf("")
        private set

    fun onBlogIdChanged(blogId: String){
        this.blogId= blogId
        Log.d("LoginViewModel", "onBlogIdChanged: ")
    }

    fun getUserBlogData(userId : String){

        viewModelScope.launch {
            fetchLoginOrCntUsecase.invoke(userId)
                .onStart { _blogIdOrCntResult.update { LoginUiState.Loading }  }
                .catch { _blogIdOrCntResult.update { LoginUiState.Error } }
                .collectLatest { value -> _blogIdOrCntResult.value = LoginUiState.Success(value)}
        }
    }
}