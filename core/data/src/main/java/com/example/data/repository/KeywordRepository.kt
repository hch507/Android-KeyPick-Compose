package com.example.data.repository

import com.example.network.model.BlogSearchDto
import com.example.network.model.MonthlySearchDto
import com.example.network.model.RelKeywordDto
import com.keypick.core.model.KeywordBlogInfoResource
import com.keypick.core.model.MonthRatioResource
import com.keypick.core.model.Rank
import com.keypick.core.model.RelKewordResource
import kotlinx.coroutines.flow.Flow

interface KeywordRepository {

    suspend fun fetchMonthlySearch(keyword : String): Flow< List<MonthRatioResource>>

    suspend fun fetchBlogPostRank(keyword : String) : Flow<Rank>

    suspend fun fetchBlogPostCountAndTrend(keyword: String) : Flow<KeywordBlogInfoResource>

    suspend fun fetchKeywordRel(keyword: String) : Flow< List<RelKewordResource>>
}