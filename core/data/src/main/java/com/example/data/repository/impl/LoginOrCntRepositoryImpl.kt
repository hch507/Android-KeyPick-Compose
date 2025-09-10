package com.example.data.repository.impl

import android.util.Log
import com.example.data.repository.LoginOrCntRepository
import com.example.network.BlogInfoNetworkDataSource
import javax.inject.Inject

class LoginOrCntRepositoryImpl @Inject constructor(
    private val blogInfoNetworkDataSource : BlogInfoNetworkDataSource
): LoginOrCntRepository{
    override suspend fun getUserBlogData(userId: String) {
        val result = blogInfoNetworkDataSource.getUserBlogInfo()
        Log.d("test_repository", "getUserBlogData: ${result}")
    }

}