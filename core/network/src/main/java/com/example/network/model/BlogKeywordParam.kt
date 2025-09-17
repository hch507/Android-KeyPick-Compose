package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogKeywordParam(
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String,
    @SerialName("timeUnit") val timeUnit: String,
    @SerialName("keywordGroups") val keywordGroups: List<KeywordGroup>
)

@Serializable
data class KeywordGroup(
    val groupName: String,
    val keywords: List<String>
)
