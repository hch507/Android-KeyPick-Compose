package com.example.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RankViewModel @Inject constructor(
    private val keywordRepository: KeywordRepository
) : ViewModel() {

    fun fetchBlogRankData(keyword : String){
        viewModelScope.launch {
            keywordRepository.fetchBlogPostRank(keyword = keyword)
        }
    }
}