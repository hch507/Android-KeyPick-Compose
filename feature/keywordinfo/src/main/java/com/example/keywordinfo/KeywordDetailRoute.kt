package com.example.keywordinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun KeywordDetailRoute(viewModel: KeywordInfoViewModel){
    val keywordInfoState by viewModel.keywordInfoState.collectAsStateWithLifecycle()
    KeywordDetailScreen()
}
@Composable
fun KeywordDetailScreen(
){

    Column {
        Text(text =" KeywordDetailScreen")
    }

}