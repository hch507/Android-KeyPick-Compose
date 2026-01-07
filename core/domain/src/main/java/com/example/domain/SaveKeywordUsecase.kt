package com.example.domain

import com.example.data.repository.KeywordStoreRepository
import javax.inject.Inject

class SaveKeywordUsecase @Inject constructor(
    private val keywordStoreRepository: KeywordStoreRepository
) {
    suspend operator fun invoke(keyword : String) =
        keywordStoreRepository.addKeyword(keyword)
}