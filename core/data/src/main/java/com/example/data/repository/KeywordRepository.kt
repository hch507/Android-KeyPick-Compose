package com.example.data.repository

interface KeywordRepository {

    suspend fun fetchMonthlySearch(keyword : String)

    suspend fun fetchBlogPostRank(keyword : String)
}