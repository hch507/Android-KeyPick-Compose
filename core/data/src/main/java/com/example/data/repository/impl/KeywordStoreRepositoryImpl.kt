package com.example.data.repository.impl


import com.example.data.repository.KeywordStoreRepository
import com.example.datastore.keywordstorepref.KeywordStorePreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class KeywordStoreRepositoryImpl @Inject constructor(
    private val keywordStorePreferencesDataSource: KeywordStorePreferencesDataSource
) : KeywordStoreRepository{
    override suspend fun getKeywords(): Flow<List<String>> = flow {
        emit(keywordStorePreferencesDataSource.getKeywords())
    }

    override suspend fun addKeyword(keyword: String) : Flow<Boolean> = flow{
        keywordStorePreferencesDataSource.addKeyword(keyword)
        emit(true)
    }

    override suspend fun deleteKeyword(keyword: String) {
        keywordStorePreferencesDataSource.deleteKeyword(keyword)
    }

}