package com.example.domain

import android.os.Build
import com.example.data.repository.KeywordRepository
import com.keypick.core.model.Rank
import com.keypick.core.model.MyRank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import android.text.Html
import androidx.annotation.RequiresApi

class FetchRankUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {

    @RequiresApi(Build.VERSION_CODES.N)
    suspend operator fun invoke(keyword: String, blogId: String): Flow<Rank> {
        return keywordRepository.fetchBlogPostRank(keyword)
            .map { allRank ->

                val cleanedList = allRank.map {
                    it.copy(title = it.title.htmlToPlainText())
                }

                val foundItem = allRank.firstOrNull {
                    it.link.contains(blogId, ignoreCase = true)
                }

                val myRank = if (foundItem != null) {
                    MyRank(
                        myRank = foundItem.rank.toString(),
                        title = foundItem.title.htmlToPlainText()
                    )
                } else {
                    MyRank(
                        myRank = "+100",
                        title = ""
                    )
                }

                Rank(
                    myRank = myRank,
                    allRank = cleanedList
                )
            }
    }


}

@RequiresApi(Build.VERSION_CODES.N)
fun String.htmlToPlainText(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}