package com.example.network.model

import com.keypick.core.model.BlogData
import com.keypick.core.model.KeywordBlogInfoResource
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

fun BlogSearchDto.asExternalModel(): KeywordBlogInfoResource = KeywordBlogInfoResource(
    totalCnt = total,
    blogData = items.map { item ->
        BlogData(
            date = item.postdate,
        )
    }
)
