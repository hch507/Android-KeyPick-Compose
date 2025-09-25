package com.example.userbloginfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.FetchBlogCntUsecase
import com.keypick.core.model.UserBlogCntData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserBlogInfoViewModel @Inject constructor(
    private val fetchBlogCntUsecase: FetchBlogCntUsecase
) : ViewModel() {

    var userBlogCntState: StateFlow<BlogCntUiState<UserBlogCntData>> = fetchBlogCntUsecase()
        .map<UserBlogCntData, BlogCntUiState<UserBlogCntData>> { data ->
            BlogCntUiState.Success(data)
        }.catch {
            emit(BlogCntUiState.Error)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BlogCntUiState.Loading
        )


}