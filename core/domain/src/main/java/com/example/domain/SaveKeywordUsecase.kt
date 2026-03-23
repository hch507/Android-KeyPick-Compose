package com.example.domain

import com.example.data.repository.KeywordStoreRepository
import com.keypick.core.model.SavedKeyword
import javax.inject.Inject

class SaveKeywordUsecase @Inject constructor(
    private val keywordStoreRepository: KeywordStoreRepository
) {
    suspend operator fun invoke(keyword : SavedKeyword) =
        keywordStoreRepository.insertKeyword(keyword)
}