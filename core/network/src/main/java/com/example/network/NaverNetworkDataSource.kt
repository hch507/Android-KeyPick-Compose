package com.example.network

import com.example.network.model.BlogSearchDto
import com.example.network.model.MonthlySearchDto

interface NaverNetworkDataSource {

    suspend fun fetchMonthlySearchVolume(keyword : String) : MonthlySearchDto

    suspend fun getRecentMonthlySearchVolume()

    suspend fun fetchBlogPostRank(keyword: String) : BlogSearchDto
}