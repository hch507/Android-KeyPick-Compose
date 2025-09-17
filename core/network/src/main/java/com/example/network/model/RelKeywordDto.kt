package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelKeywordDto(
    @SerialName("keywordList")val keywordList: List<Keyword>
)

@Serializable
data class Keyword(
    @SerialName("compIdx") val compIdx: String,
    @SerialName("monthlyAveMobileClkCnt") val monthlyAveMobileClkCnt: Double,
    @SerialName("monthlyAveMobileCtr") val monthlyAveMobileCtr: Double,
    @SerialName("monthlyAvePcClkCnt") val monthlyAvePcClkCnt: Double,
    @SerialName("monthlyAvePcCtr") val monthlyAvePcCtr: Double,
    @SerialName("monthlyMobileQcCnt") val monthlyMobileQcCnt: String,
    @SerialName("monthlyPcQcCnt") val monthlyPcQcCnt: Double,
    @SerialName("plAvgDepth") val plAvgDepth: Int,
    @SerialName("relKeyword") val relKeyword: String
)