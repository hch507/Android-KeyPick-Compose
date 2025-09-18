package com.example.keywordstore

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

@Composable
internal fun KeywordStoreRoute() {
    KeywordStoreScreen()
}

@Composable
fun KeywordStoreScreen(){
    Box(){
        Text(
            text = "키워드 저장소"
        )
    }
}