package com.example.data.repository.impl

import android.util.Log
import com.example.data.repository.KeywordRepository
import com.example.network.NaverNetworkDataSource
import com.example.network.NaverRelNetworkDataSource
import com.example.network.model.asExternalBlogInfoModel
import com.example.network.model.asExternalRankModel
import com.keypick.core.model.KeywordBlogInfoResource
import com.keypick.core.model.MonthRatioResource
import com.keypick.core.model.Rank
import com.keypick.core.model.RelKewordResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val naverNetworkDataSource: NaverNetworkDataSource,
    private val naverRelNetworkDataSource: NaverRelNetworkDataSource
) : KeywordRepository {
    override suspend fun fetchMonthlySearch(keyword: String): Flow<List<MonthRatioResource>> =
        flow {
            val result = naverNetworkDataSource.fetchMonthlySearchVolume(keyword = keyword)
            emit(result.asExternalBlogInfoModel())
        }

    override suspend fun fetchBlogPostRank(keyword: String): Flow<Rank> = flow {
        val result = naverNetworkDataSource.fetchBlogPostRank(keyword = keyword)
        emit(result.asExternalRankModel())
    }

    override suspend fun fetchBlogPostCountAndTrend(keyword: String): Flow<KeywordBlogInfoResource> =
        flow {
            val result = naverNetworkDataSource.fetchBlogPostCountAndTrend(keyword = keyword)
            emit(result.asExternalBlogInfoModel())
        }

    override suspend fun fetchKeywordRel(keyword: String): Flow<List<RelKewordResource>> = flow {
        val result = naverRelNetworkDataSource.fetchKeywordRel(keyword = keyword)
        emit(result.asExternalBlogInfoModel())
    }


}