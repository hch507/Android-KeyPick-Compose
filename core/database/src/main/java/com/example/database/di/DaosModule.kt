package com.example.database.di

import com.example.database.KeyPickDatabase
import com.example.database.dao.RecentSearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesRecentSearchDao(
        database: KeyPickDatabase,
    ): RecentSearchDao =database.recentSearchDao()
}