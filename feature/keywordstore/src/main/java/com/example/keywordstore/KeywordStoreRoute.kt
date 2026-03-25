package com.example.keywordstore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.card.AppCard
import com.keypick.core.model.SavedKeyword

@Composable
internal fun KeywordStoreRoute(
    viewModel: KeywordStoreViewModel = hiltViewModel()
) {
    val keywordState by viewModel.keywordSaveState.collectAsStateWithLifecycle()
    viewModel.getStoredKeywords()
    KeywordStoreScreen(keywordState, onDeleteClick = { viewModel.deleteKeyword(it) })
}

@Composable
fun KeywordStoreScreen(
    keywordState: KeywordStoreUiState<List<SavedKeyword>>,
    onDeleteClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            KeywordListSection(keywordState, onDeleteClick = onDeleteClick)
        }
    }
}

@Composable
fun KeywordListSection(
    keywordState: KeywordStoreUiState<List<SavedKeyword>>,
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
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val count = when (keywordState) {
                        is KeywordStoreUiState.Success -> keywordState.data.size
                        else -> 0
                    }
                    Text(text = stringResource(R.string.store_title), fontSize = 15.sp, modifier = Modifier.weight(1f))
                    Text(text = stringResource(R.string.store_count, count), fontSize = 15.sp)
                }
            }
        }
        when (keywordState) {
            is KeywordStoreUiState.Success -> {
                val savedKeywords = keywordState.data
                items(
                    items = savedKeywords,
                    key = { it.keyword }
                ) { savedKeyword ->
                    SavedKeywordItem(savedKeyword = savedKeyword, onDeleteClick = onDeleteClick)
                }
            }

            KeywordStoreUiState.Loading -> {

            }

            KeywordStoreUiState.Error -> {
                item {
                    ErrorScreen()
                }
            }
        }

    }
}


@Composable
fun ErrorScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.store_load_error), color = MaterialTheme.colorScheme.error)
    }
}
@Composable
fun SavedKeywordItem(
    savedKeyword: SavedKeyword,
    onDeleteClick: (String) -> Unit
) {
    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = savedKeyword.keyword, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                IconButton(
                    onClick = {
                        onDeleteClick(savedKeyword.keyword)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(com.example.designsystem.R.drawable.ic_releted_search),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "${savedKeyword.resultCount}회",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp,
                )

            }

        }
    }
}


