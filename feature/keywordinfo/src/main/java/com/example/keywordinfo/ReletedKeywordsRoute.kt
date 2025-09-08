package com.example.keywordinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ReletedKeywordsRoute(){
    RelatedKeywordsScreen()
}

@Composable
fun RelatedKeywordsScreen(){
    Column {
        Text(text ="RelatedKeywordsScreen")
    }
}