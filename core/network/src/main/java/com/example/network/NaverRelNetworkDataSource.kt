package com.example.network

import com.example.network.model.RelKeywordDto

interface NaverRelNetworkDataSource {

    suspend fun fetchKeywordRel(keyword: String) : RelKeywordDto
}