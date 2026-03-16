package com.example.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.R
import com.example.designsystem.textfield.SearchTextField
import com.keypick.core.model.RecentSearch

@Composable
internal fun SearchRoute(
    onSearchClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val searchKeyword = viewModel.searchKeyword
    val recentSearches by viewModel.recentSearches.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigateEvent.collect { keyword ->
            onSearchClick(keyword) // insert 완료 후 화면 이동
        }
    }
    SearchScreen(
        onSearchClick = {
            viewModel.onSearchClick(searchKeyword)
        },
        onSearchKeywordChange = {
            viewModel.onSearchKeywordChanged(it)
        },
        onBackClick = onBackClick,
        searchKeyword = searchKeyword,
        recentSearchsUiState = recentSearches,
        onDeleteRecentSearch = {viewModel.deleteRecentSearch(it)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onSearchClick: () -> Unit,
    onSearchKeywordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    searchKeyword: String,
    recentSearchsUiState: SearchUiState<List<RecentSearch>>,
    onDeleteRecentSearch: (String) -> Unit
) {
    Scaffold(
        topBar = {
            SearchTopBar(
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7))
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                SearchTextField(
                    value = searchKeyword,
                    onValueChange = { newValue -> onSearchKeywordChange(newValue) },
                    onSearch = onSearchClick
                )

                Spacer(modifier = Modifier.height(12.dp))
                RecentSearchSection(
                    recentSearchsUiState = recentSearchsUiState,
                    onDeleteRecentSearch = onDeleteRecentSearch
                )
            }
        }
    }

}

@Composable
fun RecentSearchSection(
    recentSearchsUiState: SearchUiState<List<RecentSearch>>,
    onDeleteRecentSearch: (String) -> Unit
) {

    when (recentSearchsUiState) {

        SearchUiState.Idle -> {}

        SearchUiState.Loading -> {
            Text("로딩중...")
        }

        SearchUiState.Error -> {
            Text("최근 검색어를 불러오지 못했습니다.")
        }

        is SearchUiState.Success -> {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(20.dp))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp)
            ) {

                RecentSearchHeader(onDeleteAll = {})

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = recentSearchsUiState.data,
                        key = { it.keyword }
                    ) { search ->
                        RecentSearchItem(
                            text = search.keyword,
                            onDeleteItem = onDeleteRecentSearch
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun RecentSearchItem(
    text: String,
    onDeleteItem: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "remove",
            modifier = Modifier
                .size(18.dp)
                .clickable { onDeleteItem(text) },
            tint = Color.Gray
        )
    }
}

@Composable
fun RecentSearchHeader(
    onDeleteAll: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "최근 검색어",
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "전체 삭제",
            color = Color.Gray,
            modifier = Modifier.clickable {
                onDeleteAll()
            }
        )
    }
}

@Composable
fun SearchTopBar(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .statusBarsPadding()
            .padding(vertical = 8.dp, horizontal = 12.dp),

        ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            "검색",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
        )
    }

}