package com.example.data.repository

interface KeywordRepository {

    suspend fun fetchMonthlySearch(keyword : String)

    suspend fun fetchBlogPostRank(keyword : String)

    suspend fun fetchBlogPostCountAndTrend(keyword: String)

    suspend fun fetchKeywordRel(keyword: String)
}