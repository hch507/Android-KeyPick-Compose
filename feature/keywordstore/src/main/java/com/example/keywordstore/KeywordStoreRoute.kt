package com.example.keywordstore

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
internal fun KeywordStoreRoute(
     viewModel: KeywordStoreViewModel = hiltViewModel()
) {
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