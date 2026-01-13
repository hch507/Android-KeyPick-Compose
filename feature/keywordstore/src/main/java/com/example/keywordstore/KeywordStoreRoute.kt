package com.example.keywordstore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun KeywordStoreRoute(
     viewModel: KeywordStoreViewModel = hiltViewModel()
) {
    val keywordState by viewModel.keywordSaveState.collectAsStateWithLifecycle()
    KeywordStoreScreen(keywordState)
}

@Composable
fun KeywordStoreScreen(
    keywordState: KeywordStoreUiState<List<String>>,
){
    Box(
        modifier = Modifier.padding(20.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text("보관함")

            KeywordListSection(keywordState, onDeleteClick = {})
        }
    }
}
@Composable
fun KeywordListSection(
    keywordState: KeywordStoreUiState<List<String>>,
    modifier: Modifier = Modifier,
    onDeleteClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 헤더
        item {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    val count = when (keywordState) {
                        is KeywordStoreUiState.Success -> keywordState.data.size
                        else -> 0
                    }
                    Text(text = "저장 키워드", fontSize = 15.sp, modifier = Modifier.weight(1f))
                    Text(text = "${count}개", fontSize = 15.sp)
                }
            }
        }
        when (keywordState) {
            is KeywordStoreUiState.Success -> {
                val keywords = keywordState.data
                items(
                    items = keywords,
                    key = { it }
                ) { keyword ->
                    StoredKeywordItem(keyword = keyword, onDeleteClick = onDeleteClick)
                }
            }
            KeywordStoreUiState.Loading -> {

            }
            KeywordStoreUiState.Error -> {
                item {
                    Text("오류 발생", color = Color.Red)
                }
            }
        }

    }
}



@Composable
fun StoredKeywordItem(
    keyword : String,
    onDeleteClick : (String) -> Unit
){
    KeywordItemBox {
        Text(text = keyword)
    }

}
@Composable
fun KeywordItemBox(
    modifier: Modifier = Modifier.padding(horizontal = 15.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

