package com.example.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.SearchRepository
import com.keypick.core.model.RecentSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    var searchKeyword by mutableStateOf("")
        private set
    val recentSearches: StateFlow<SearchUiState<List<RecentSearch>>> =
        searchRepository.getRecentSearches()
            .map<List<RecentSearch>, SearchUiState<List<RecentSearch>>> {
                SearchUiState.Success(it)
            }
            .onStart {
                emit(SearchUiState.Loading)
            }
            .catch {
                emit(SearchUiState.Error)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SearchUiState.Idle
            )

    private val _navigateEvent = MutableSharedFlow<String>()
    val navigateEvent = _navigateEvent.asSharedFlow()

    fun onSearchKeywordChanged(searchKeyword: String){
        this.searchKeyword= searchKeyword
        Log.d("LoginViewModel", "onBlogIdChanged: ")
    }

    fun onSearchClick(searchKeyword: String) {
        viewModelScope.launch {
            searchRepository.insertSearch(searchKeyword, System.currentTimeMillis())
            // DB insert 끝나면 이벤트 발생
            _navigateEvent.emit(searchKeyword)
        }
    }
    fun deleteRecentSearch(keyword: String) {
        viewModelScope.launch {
            searchRepository.deleteSearch(keyword)
        }
    }

}