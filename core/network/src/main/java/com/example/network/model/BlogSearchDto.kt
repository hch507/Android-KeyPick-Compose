package com.example.network.model

import com.keypick.core.model.AllRank
import com.keypick.core.model.BlogData
import com.keypick.core.model.KeywordBlogInfoResource
import com.keypick.core.model.Rank
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogSearchDto(
    @SerialName("display") val display: Int,
    @SerialName("items") val items: List<Item>,
    @SerialName("lastBuildDate") val lastBuildDate: String,
    @SerialName("start") val start: Int,
    @SerialName("total") val total: Int
)

@Serializable
data class Item(
    @SerialName("bloggerlink") val bloggerlink: String,
    @SerialName("bloggername") val bloggername: String,
    @SerialName("description") val description: String,
    @SerialName("link") val link: String,
    @SerialName("postdate") val postdate: String,
    @SerialName("title") val title: String
)

fun BlogSearchDto.asExternalBlogInfoModel(): KeywordBlogInfoResource = KeywordBlogInfoResource(
    totalCnt = total,
    blogData = items.map { item ->
        BlogData(
            date = item.postdate,
        )
    }
)

fun BlogSearchDto.asExternalRankModel(): List<AllRank> {
    return items.mapIndexed {index, item->
        AllRank(
            rank = index+1,
            title = item.title,
            link = item.bloggerlink
        )
    }

}
