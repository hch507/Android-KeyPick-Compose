package com.example.network.model

import com.example.network.utils.StringAsAnySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelKeywordDto(
    @SerialName("keywordList")val keywordList: List<Keyword>
)

@Serializable
data class Keyword(
    @SerialName("compIdx") @Serializable(with = StringAsAnySerializer::class) val compIdx: String,
    @SerialName("monthlyAveMobileClkCnt") @Serializable(with = StringAsAnySerializer::class) val monthlyAveMobileClkCnt: String,
    @SerialName("monthlyAveMobileCtr")  @Serializable(with = StringAsAnySerializer::class)val monthlyAveMobileCtr: String,
    @SerialName("monthlyAvePcClkCnt")  @Serializable(with = StringAsAnySerializer::class)val monthlyAvePcClkCnt: String,
    @SerialName("monthlyAvePcCtr") @Serializable(with = StringAsAnySerializer::class) val monthlyAvePcCtr: String,
    @SerialName("monthlyMobileQcCnt")  @Serializable(with = StringAsAnySerializer::class)val monthlyMobileQcCnt: String,
    @SerialName("monthlyPcQcCnt")  @Serializable(with = StringAsAnySerializer::class)val monthlyPcQcCnt: String,
    @SerialName("plAvgDepth")  @Serializable(with = StringAsAnySerializer::class)val plAvgDepth: String,
    @SerialName("relKeyword")  @Serializable(with = StringAsAnySerializer::class)val relKeyword: String
)