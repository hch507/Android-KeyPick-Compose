package com.example.data.repository

import com.keypick.core.model.SavedKeyword
import kotlinx.coroutines.flow.Flow

interface KeywordStoreRepository {

    suspend fun getKeywords() : Flow<List<SavedKeyword>>

    suspend fun insertKeyword(keyword : SavedKeyword) :Flow<Boolean>

    suspend fun deleteKeyword(keyword: String)
}