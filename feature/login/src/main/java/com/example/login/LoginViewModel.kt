package com.example.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.LoginOrCntRepository
import com.example.domain.FetchLoginOrCntUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
//    private val loginOrCntRepository: LoginOrCntRepository
    private val fetchLoginOrCntUsecase: FetchLoginOrCntUsecase
) : ViewModel(){
    private val _blogIdOrCntResult = MutableStateFlow<LoginUiState<String>>(LoginUiState.Loading)
    val blogIdOrCntResult = _blogIdOrCntResult.asStateFlow()
    var blogId by mutableStateOf("")
        private set

    fun onBlogIdChanged(blogId: String){
        this.blogId= blogId
        Log.d("LoginViewModel", "onBlogIdChanged: ")
    }

    fun getUserBlogData(userId : String){
        Log.d("getUserBlogData", "getUserBlogData: ")
        viewModelScope.launch {
//            fetchLoginOrCntUsecase.invoke(userId)
        }
    }

}