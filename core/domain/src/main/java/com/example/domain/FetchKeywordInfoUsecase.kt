package com.example.domain

import com.example.data.repository.KeywordRepository
import com.keypick.core.model.KeywordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class FetchKeywordInfoUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {
    operator suspend fun invoke(keyword: String): Flow<KeywordInfo> {
        val monthlySearchFlow = keywordRepository.fetchMonthlySearch(keyword)
        val blogTrendFlow = keywordRepository.fetchBlogPostCountAndTrend(keyword)
        val relKeywordFlow = keywordRepository.fetchKeywordRel(keyword)

        return combine(
            monthlySearchFlow,
            blogTrendFlow,
            relKeywordFlow
        ) { monthlySearch, blogTrend, relKeyword ->

            KeywordInfo(
                keywordBlogInfoResource = blogTrend,
                relKewordResource = relKeyword,
                monthRatioResource = monthlySearch[0]
            )
        }
    }
}