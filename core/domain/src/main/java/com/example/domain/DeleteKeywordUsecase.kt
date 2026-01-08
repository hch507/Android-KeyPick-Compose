package com.example.domain

import com.example.data.repository.KeywordStoreRepository
import javax.inject.Inject

class DeleteKeywordUsecase @Inject constructor(
    private val keywordStoreRepository: KeywordStoreRepository
){
    suspend operator fun invoke(keyword: String){
        keywordStoreRepository.deleteKeyword(keyword)
    }
}