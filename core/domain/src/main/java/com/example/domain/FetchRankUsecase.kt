package com.example.domain

import com.example.data.repository.KeywordRepository
import com.keypick.core.model.Rank
import com.keypick.core.model.MyRank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchRankUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {

    suspend operator fun invoke(keyword: String, blogId: String): Flow<Rank> {
        return keywordRepository.fetchBlogPostRank(keyword)
            .map { allRank ->



                val foundItem = allRank.firstOrNull {
                    it.link.contains(blogId, ignoreCase = true)
                }

                val myRank = if (foundItem != null) {
                    MyRank(
                        myRank = foundItem.rank.toString(),
                        title = foundItem.title
                    )
                } else {
                    MyRank(
                        myRank = "+100",
                        title = ""
                    )
                }

                Rank(
                    myRank = myRank,
                    allRank = allRank
                )
            }
    }


}