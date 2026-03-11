package com.example.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.KeywordRepository
import com.example.data.repository.LoginOrCntRepository
import com.example.domain.FetchRankUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class RankViewModel @Inject constructor(
    private val fetchRankUsecase: FetchRankUsecase,

) : ViewModel() {
    private val _rankResult = MutableStateFlow<RankUiState<Int>>(RankUiState.Loading)
    val rankResult = _rankResult.asStateFlow()
    fun fetchBlogRankData(keyword : String, blogId : String){
        Log.d("fetchBlogRankData", "fetchBlogRankData:${keyword} ${blogId}")
        viewModelScope.launch {
            fetchRankUsecase.invoke(keyword=keyword , blogId = blogId)
                .onStart { _rankResult.update { RankUiState.Loading } }
                .catch { throwable ->
                    Log.d("DEBUG_RANK", "Error occurred", throwable)
                    _rankResult.update { RankUiState.Error } }
                .collectLatest { value ->
                    _rankResult.value = RankUiState.Success(value)
                }
        }
    }
}