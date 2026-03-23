package com.example.data.repository.impl


import com.example.data.repository.KeywordStoreRepository
import com.example.database.dao.SavedKeywordDao
import com.example.database.model.SavedKeywordEntity
import com.example.database.model.toDomain
import com.example.datastore.keywordstorepref.KeywordStorePreferencesDataSource
import com.keypick.core.model.SavedKeyword
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class KeywordStoreRepositoryImpl @Inject constructor(
    private val savedKeywordDao: SavedKeywordDao
) : KeywordStoreRepository{
    override suspend fun getKeywords(): Flow<List<SavedKeyword>> {
        return savedKeywordDao.getSavedKeyword().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertKeyword(keyword: SavedKeyword)  = flow{
        savedKeywordDao.insertKeyword(SavedKeywordEntity(
            keyword= keyword.keyword,
            timestamp = keyword.timestamp,
            resultCount = keyword.resultCount
        ))
        emit(true)
    }

    override suspend fun deleteKeyword(keyword: String) {
        savedKeywordDao.deleteKeyword(keyword)
    }

}