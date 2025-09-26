package com.example.data.repository

import com.keypick.core.model.UserBlogCntData
import kotlinx.coroutines.flow.Flow

interface LoginOrCntRepository {
    fun fetchUserBlogData(): Flow<UserBlogCntData>

    suspend fun hasBlogId(userId: String): Flow<Boolean>

    suspend fun fetchlogout(): Flow<Boolean>
}