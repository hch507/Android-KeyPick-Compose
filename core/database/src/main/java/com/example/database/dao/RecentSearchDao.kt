package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    // 최근 검색어 조회 (최신순)
    @Query("SELECT * FROM recent_search ORDER BY timestamp DESC")
    fun getRecentSearches(): Flow<List<RecentSearchEntity>>

    // 검색어 추가 (같은 keyword 있으면 timestamp 업데이트)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: RecentSearchEntity)

    // 특정 검색어 삭제 (x 버튼)
    @Query("DELETE FROM recent_search WHERE keyword = :keyword")
    suspend fun deleteSearch(keyword: String)

    // 전체 삭제
    @Query("DELETE FROM recent_search")
    suspend fun deleteAll()

}