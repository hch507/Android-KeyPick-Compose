package com.example.domain

import com.example.data.repository.LoginOrCntRepository
import javax.inject.Inject

class FetchLogoutUsecase @Inject constructor(
    private val loginOnCntRepository: LoginOrCntRepository
) {
    suspend operator fun invoke() =
        loginOnCntRepository.fetchLogout()
}