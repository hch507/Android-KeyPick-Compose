package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.RecentSearchEntity
import com.example.database.model.SavedKeywordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedKeywordDao {

    @Query("SELECT * FROM saved_keyword ORDER BY timestamp DESC")
    fun getSavedKeyword(): Flow<List<SavedKeywordEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyword(keyword: SavedKeywordEntity)

    @Query("DELETE FROM saved_keyword WHERE keyword = :keyword")
    suspend fun deleteKeyword(keyword: String)
}