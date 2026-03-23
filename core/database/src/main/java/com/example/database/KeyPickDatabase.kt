package com.example.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.RecentSearchDao
import com.example.database.dao.SavedKeywordDao
import com.example.database.model.RecentSearchEntity
import com.example.database.model.SavedKeywordEntity

@Database(
    entities = [RecentSearchEntity::class, SavedKeywordEntity::class],
    version = 1,
    exportSchema = true
)
abstract class KeyPickDatabase :RoomDatabase(){

    abstract fun recentSearchDao() : RecentSearchDao

    abstract fun savedKeywordDao() : SavedKeywordDao
}