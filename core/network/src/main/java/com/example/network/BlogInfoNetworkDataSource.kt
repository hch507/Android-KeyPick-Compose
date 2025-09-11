package com.example.network

import com.example.network.model.LoginOrCntDto

interface BlogInfoNetworkDataSource {

    suspend fun getUserBlogInfo(blogId : String) : LoginOrCntDto
}