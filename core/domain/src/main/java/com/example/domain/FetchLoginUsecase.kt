package com.example.domain

import com.example.data.repository.LoginOrCntRepository
import javax.inject.Inject

class FetchLoginUsecase @Inject constructor(
    private val loginOnCntRepository: LoginOrCntRepository
) {
    operator suspend fun invoke(blogId: String) =
        loginOnCntRepository.hasBlogId(blogId)
}