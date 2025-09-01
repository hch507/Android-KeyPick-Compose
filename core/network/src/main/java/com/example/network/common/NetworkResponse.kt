package com.example.network.common

import kotlinx.serialization.Serializable

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)
