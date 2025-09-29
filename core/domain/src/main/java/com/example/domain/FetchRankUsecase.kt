package com.example.domain

import com.example.data.repository.KeywordRepository
import com.keypick.core.model.Rank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchRankUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {

    suspend operator fun invoke(keyword: String, blogId: String): Flow<Int> {
        return keywordRepository.fetchBlogPostRank(keyword)
            .map { rank: Rank -> // 👈 Rank 타입 명시 (optional)
                val index = rank.blogLink.indexWithKeyword(keyword)
                index!!
            }
    }

    private fun List<String>.indexWithKeyword(keyword: String): Int? {
        return this.indexOfFirst { it.contains(keyword, ignoreCase = true) }
            .takeIf { it != -1 }
    }
}