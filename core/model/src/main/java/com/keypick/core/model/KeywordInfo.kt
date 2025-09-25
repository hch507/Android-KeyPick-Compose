package com.keypick.core.model

data class KeywordInfo(
    val keywordBlogInfoResource: KeywordBlogInfoResource,
    val relKewordResource: List<RelKewordResource>,
    val monthRatioResource: MonthRatioResource
)

data class RelKewordResource(
    val relKeyword: String,
    val monthlyPcQcCnt: String,
    val monthlyMobileQcCnt: String
)

data class KeywordBlogInfoResource(
    var totalCnt: Int,
    var blogData: List<BlogData>
)

data class BlogData(
    var date: String
)

data class MonthRatioResource(
    var title: String?,
    var ratioData: List<RatioData>?
)

data class RatioData(
    val period: String?,
    val rate: Double?
)


