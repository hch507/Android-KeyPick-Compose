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
        return keywordRepository.fetchBlogPostRank(blogId)
            .map { rank: Rank ->
                val index = rank.blogLink.indexWithKeyword(keyword)
                index ?: -1
            }
    }

    private fun List<String>.indexWithKeyword(blogId: String): Int? {
        return this.indexOfFirst { it.contains(blogId, ignoreCase = true) }
            .takeIf { it != -1 }
    }
}