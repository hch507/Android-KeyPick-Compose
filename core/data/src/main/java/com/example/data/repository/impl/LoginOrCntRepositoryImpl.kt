package com.example.data.repository.impl

import com.example.data.repository.LoginOrCntRepository
import com.example.datastore.BlogPreferencesDataSource
import com.example.network.BlogInfoNetworkDataSource
import com.example.network.model.asExternalModel
import com.keypick.core.model.UserBlogCntData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class LoginOrCntRepositoryImpl @Inject constructor(
    private val blogInfoNetworkDataSource: BlogInfoNetworkDataSource,
    private val userBlogPreferencesDataSource: BlogPreferencesDataSource
) : LoginOrCntRepository {
    override suspend fun getUserBlogData(userBlogId: String): Flow<UserBlogCntData> = flow {
        val result = blogInfoNetworkDataSource.getUserBlogInfo(blogId = userBlogId)
        userBlogPreferencesDataSource.saveBlogId(userBlogId)
        emit(result.asExternalModel())
    }

}