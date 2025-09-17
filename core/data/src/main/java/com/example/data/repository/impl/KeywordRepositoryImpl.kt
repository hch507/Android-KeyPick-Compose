package com.example.data.repository.impl

import android.util.Log
import com.example.data.repository.KeywordRepository
import com.example.network.NaverNetworkDataSource
import com.example.network.NaverRelNetworkDataSource
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val naverNetworkDataSource: NaverNetworkDataSource,
    private val naverRelNetworkDataSource: NaverRelNetworkDataSource
) : KeywordRepository {
    override suspend fun fetchMonthlySearch(keyword: String) {
        val result = naverNetworkDataSource.fetchMonthlySearchVolume(keyword = keyword)
        Log.d("test_repository", "fetchMonthlySearch:")
    }

    override suspend fun fetchBlogPostRank(keyword: String) {
        val result = naverNetworkDataSource.fetchBlogPostRank(keyword = keyword)
        Log.d("test_repository", "fetchBlogPostRank:")
    }

    override suspend fun fetchBlogPostCountAndTrend(keyword: String) {
        val result = naverNetworkDataSource.fetchBlogPostCountAndTrend(keyword = keyword)
        Log.d("test_repository", "fetchBlogPostCountAndTrend:")
    }

    override suspend fun fetchKeywordRel(keyword: String) {
        val result = naverRelNetworkDataSource.fetchKeywordRel(keyword = keyword)
        Log.d("test_repository", "fetchKeywordRel:")
    }

}