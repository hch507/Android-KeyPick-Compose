package com.example.userbloginfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.repository.KeywordRepository
import com.example.domain.CheckLoginedUsecase
import com.example.domain.FetchBlogCntUsecase
import com.example.domain.FetchLogoutUsecase
import com.keypick.core.model.UserBlogCntData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
class UserBlogInfoViewModel @Inject constructor(
    private val fetchBlogCntUsecase: FetchBlogCntUsecase,
    private val fetchLogoutUsecase: FetchLogoutUsecase,
    private val keywordRepository: KeywordRepository,
) : ViewModel() {

    private val _logoutEvent = MutableSharedFlow<Unit>()
    val logoutEvent = _logoutEvent.asSharedFlow()

    private val _recommendEvent = MutableSharedFlow<String?>()
    val recommendEvent = _recommendEvent.asSharedFlow()


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
    private val _logoutState = MutableStateFlow<LogoutState<Boolean>>(LogoutState.Loading)
    val logoutState = _logoutState.asStateFlow()

    private val _recommendKeywordState = MutableStateFlow<RecommendUiState<String?>>(RecommendUiState.Loading)
    val recommendKeywordState = _recommendKeywordState.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            fetchLogoutUsecase.invoke()
                .onStart { _logoutState.update { LogoutState.Loading } }
                .catch { _logoutState.update { LogoutState.Error } }
                .collectLatest { value ->
                    _logoutState.value = LogoutState.Success(value)
                    _logoutEvent.emit(Unit)
                }
        }

    }
    fun getRecommendKeyword() {
        viewModelScope.launch {
            keywordRepository.getRecommendKeyword()
                .onStart { _recommendKeywordState.update { RecommendUiState.Loading } }
                .catch {_recommendKeywordState.update { RecommendUiState.Error }  }
                .collectLatest { value ->
                    _recommendKeywordState.value = RecommendUiState.Success(value)
                    _recommendEvent.emit(value)
                }

            // 키워드 처리
        }
    }

}