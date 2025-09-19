package com.example.domain

import com.example.data.repository.KeywordRepository
import javax.inject.Inject

class FetchKeywordInfoUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {
    operator suspend fun invoke(keyword: String){
        keywordRepository.fetchMonthlySearch(keyword = keyword)
        keywordRepository.fetchBlogPostCountAndTrend(keyword = keyword)
        keywordRepository.fetchKeywordRel(keyword = keyword)
    }
}