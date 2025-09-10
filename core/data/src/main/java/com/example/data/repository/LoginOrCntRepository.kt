package com.example.data.repository

interface LoginOrCntRepository {
    suspend fun getUserBlogData(userId : String)
}