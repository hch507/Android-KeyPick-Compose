package com.example.data.di

import com.example.data.repository.KeywordRepository
import com.example.data.repository.KeywordStoreRepository
import com.example.data.repository.LoginOrCntRepository
import com.example.data.repository.impl.KeywordRepositoryImpl
import com.example.data.repository.impl.KeywordStoreRepositoryImpl
import com.example.data.repository.impl.LoginOrCntRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLoginOrCntRepository(
        loginOrCntRepository: LoginOrCntRepositoryImpl
    ) : LoginOrCntRepository

    @Binds
    abstract fun bindKeywordRepository(
        keywordRepository: KeywordRepositoryImpl
    ) : KeywordRepository

    @Binds
    abstract fun bindKeywordDataStoreRepository(
        keywordStoreRepository: KeywordStoreRepositoryImpl
    ): KeywordStoreRepository
}