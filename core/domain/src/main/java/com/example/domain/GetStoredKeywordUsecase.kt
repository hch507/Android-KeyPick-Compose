package com.example.domain

import com.example.data.repository.KeywordStoreRepository
import javax.inject.Inject

class GetStoredKeywordUsecase @Inject constructor(
    private val keywordStoreRepository: KeywordStoreRepository
) {

    suspend operator fun invoke() = keywordStoreRepository.getKeywords()
}