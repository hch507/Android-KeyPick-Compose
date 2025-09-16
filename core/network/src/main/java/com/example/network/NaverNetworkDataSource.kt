package com.example.network

import com.example.network.model.MonthlySearchDto

interface NaverNetworkDataSource {

    suspend fun fetchMonthlySearchVolume(keyword : String) : MonthlySearchDto

    suspend fun getRecentMonthlySearchVolume()

    suspend fun getBlogPostRank()
}