package com.example.domain

import com.example.data.repository.LoginOrCntRepository
import javax.inject.Inject

class CheckLoginedUsecase @Inject constructor(
    private val loginOnCntRepository: LoginOrCntRepository
) {
    operator fun invoke() =
        loginOnCntRepository.getBlogId()
}