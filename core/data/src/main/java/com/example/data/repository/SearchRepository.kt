package com.example.data.repository

import com.example.database.model.RecentSearchEntity
import com.keypick.core.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getRecentSearches() : Flow<List<RecentSearch>>

    suspend fun insertSearch(keyword : String, timestamp:Long)

    suspend fun deleteSearch(keyword:String)

    suspend fun deleteAll()
}