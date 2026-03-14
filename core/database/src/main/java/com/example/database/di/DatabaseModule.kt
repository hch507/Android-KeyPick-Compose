package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.KeyPickDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesKeyPickDatabase(
        @ApplicationContext context : Context,
    ):KeyPickDatabase = Room.databaseBuilder(
        context,
        KeyPickDatabase::class.java,
        "keypick-database"
    ).build()

}