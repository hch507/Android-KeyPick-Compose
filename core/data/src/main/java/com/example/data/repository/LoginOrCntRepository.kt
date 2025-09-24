package com.example.data.repository

import com.keypick.core.model.UserBlogCntData
import kotlinx.coroutines.flow.Flow

interface LoginOrCntRepository {
    suspend fun getUserBlogData(userId : String) : Flow<UserBlogCntData>
}