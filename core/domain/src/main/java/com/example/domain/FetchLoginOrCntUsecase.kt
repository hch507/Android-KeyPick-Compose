package com.example.domain

import com.example.data.repository.LoginOrCntRepository
import javax.inject.Inject

class FetchLoginOrCntUsecase @Inject constructor(
    private val loginOnCntRepository : LoginOrCntRepository
){
    operator suspend fun invoke(blogId : String){
        loginOnCntRepository.getUserBlogData(blogId)
    }
}