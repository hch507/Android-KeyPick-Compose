package com.example.domain

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.repository.KeywordRepository
import com.keypick.core.model.KeywordBlogCountInfo
import com.keypick.core.model.KeywordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FetchKeywordInfoUsecase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator suspend fun invoke(keyword: String): Flow<KeywordInfo> {
        val monthlySearchFlow = keywordRepository.fetchMonthlySearch(keyword)
        val blogTrendFlow = keywordRepository.fetchBlogPostCountAndTrend(keyword)
        val relKeywordFlow = keywordRepository.fetchKeywordRel(keyword)

        return combine(
            monthlySearchFlow,
            blogTrendFlow,
            relKeywordFlow
        ) { monthlySearch, blogTrend, relKeyword ->

            val count = countLast30Days(
                blogTrend.blogData.map { it.date }
            )
            val blogCountInfo = KeywordBlogCountInfo(blogTrend.totalCnt, count)
            KeywordInfo(
                keywordBlogInfoResource = blogCountInfo,
                relKeywordResource = relKeyword,
                monthRatioResource = monthlySearch[0]
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun countLast30Days(list: List<String>): Int {
    val thirtyDaysAgo = LocalDate.now().minusDays(30)
    return list.count {
        LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyyMMdd")) >= thirtyDaysAgo
    }
}