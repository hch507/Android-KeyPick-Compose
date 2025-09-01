package com.example.network

interface NaverNetworkDataSource {

    suspend fun getMonthlySearchVolume()

    suspend fun getRecentMonthlySearchVolume()

    suspend fun getBlogPostRank()
}