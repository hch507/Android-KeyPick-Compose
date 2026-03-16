package com.example.data.repository.impl

import com.example.data.repository.SearchRepository
import com.example.database.dao.RecentSearchDao
import com.example.database.model.RecentSearchEntity
import com.example.database.model.toDomain
import com.keypick.core.model.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
) : SearchRepository {
    override fun getRecentSearches(): Flow<List<RecentSearch>> {
        return recentSearchDao.getRecentSearches()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun insertSearch(keyword: String, timestamp: Long) {
        recentSearchDao.insertSearch(RecentSearchEntity(
            keyword=keyword,
            timestamp=timestamp
        ))
    }


    override suspend fun deleteSearch(keyword: String) {
        recentSearchDao.deleteSearch(keyword)
    }

    override suspend fun deleteAll() {
        recentSearchDao.deleteAll()
    }


}