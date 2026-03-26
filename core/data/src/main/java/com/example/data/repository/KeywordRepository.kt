package com.example.data.repository

import com.keypick.core.model.AllRank
import com.keypick.core.model.KeywordBlogInfoResource
import com.keypick.core.model.MonthRatioResource
import com.keypick.core.model.RelKeywordResource
import kotlinx.coroutines.flow.Flow

interface KeywordRepository {

    suspend fun fetchMonthlySearch(keyword: String): Flow<List<MonthRatioResource>>

    suspend fun fetchBlogPostRank(keyword: String): Flow<List<AllRank>>

    suspend fun fetchBlogPostCountAndTrend(keyword: String): Flow<KeywordBlogInfoResource>

    suspend fun fetchKeywordRel(keyword: String): Flow<List<RelKeywordResource>>

    suspend fun getRecommendKeyword(): Flow<String?>
}