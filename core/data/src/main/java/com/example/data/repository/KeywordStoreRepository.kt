package com.example.data.repository

import kotlinx.coroutines.flow.Flow

interface KeywordStoreRepository {

    suspend fun getKeywords() : Flow<List<String>>

    suspend fun addKeyword(keyword : String) : Flow<Boolean>

    suspend fun deleteKeyword(keyword: String)
}