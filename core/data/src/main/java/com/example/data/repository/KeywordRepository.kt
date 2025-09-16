package com.example.data.repository

interface KeywordRepository {

    suspend fun fetchMonthlySearch(keyword : String)
}