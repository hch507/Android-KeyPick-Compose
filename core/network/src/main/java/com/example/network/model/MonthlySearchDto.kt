package com.example.network.model

import com.keypick.core.model.MonthRatioResource
import com.keypick.core.model.RatioData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MonthlySearchDto(
    @SerialName("startDate") val startDate: String,
    @SerialName("endDate") val endDate: String,
    @SerialName("timeUnit") val timeUnit: String,
    @SerialName("results") var results: List<Result>
)

@Serializable
data class Result(
    @SerialName("data") val data: List<Data>,
    @SerialName("keywords") val keywords: List<String>,
    @SerialName("title") val title: String
)

@Serializable
data class Data(
    @SerialName("period") val period: String,
    @SerialName("ratio") val ratio: Double
)

fun MonthlySearchDto.asExternalModel(): List<MonthRatioResource> =
    results.map {
        MonthRatioResource(
            title = it.title,
            ratioData = it.data.map { data ->
                RatioData(
                    period = data.period,
                    rate = data.ratio
                )
            }
        )
    }