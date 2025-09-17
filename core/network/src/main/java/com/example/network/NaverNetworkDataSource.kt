package com.example.network

import com.example.network.model.BlogSearchDto
import com.example.network.model.MonthlySearchDto

interface NaverNetworkDataSource {

    suspend fun fetchMonthlySearchVolume(keyword : String) : MonthlySearchDto

    suspend fun fetchBlogPostCountAndTrend(keyword : String) : BlogSearchDto

    suspend fun fetchBlogPostRank(keyword: String) : BlogSearchDto
}