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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
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
        onDeleteRecentSearch = { viewModel.deleteRecentSearch(it) },
        onDeleteAll = {viewModel.deleteAll()}
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
    onDeleteRecentSearch: (String) -> Unit,
    onDeleteAll: () -> Unit
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
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                SearchTextField(
                    value = searchKeyword,
                    onValueChange = { newValue -> onSearchKeywordChange(newValue) },
                    onSearch = onSearchClick,
                    placeholder = stringResource(com.example.search.R.string.search_hint)
                )

                Spacer(modifier = Modifier.height(12.dp))
                RecentSearchSection(
                    recentSearchsUiState = recentSearchsUiState,
                    onDeleteRecentSearch = onDeleteRecentSearch,
                    onDeleteAll = onDeleteAll
                )
            }
        }
    }

}

@Composable
fun RecentSearchSection(
    recentSearchsUiState: SearchUiState<List<RecentSearch>>,
    onDeleteRecentSearch: (String) -> Unit,
    onDeleteAll: () -> Unit
) {

    when (recentSearchsUiState) {

        SearchUiState.Idle -> {}

        SearchUiState.Loading -> {
            Text(stringResource(com.example.search.R.string.search_loading))
        }

        SearchUiState.Error -> {
            Text(stringResource(com.example.search.R.string.search_error))
        }

        is SearchUiState.Success -> {
            val data = recentSearchsUiState.data
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(20.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp)
            ) {

                RecentSearchHeader(onDeleteAll = onDeleteAll)

                Spacer(modifier = Modifier.height(8.dp))


                if (data.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(com.example.search.R.string.recent_search_empty),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            items = data,
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
            text = stringResource(com.example.search.R.string.search_recent),
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = stringResource(com.example.search.R.string.search_delete_all),
            color = MaterialTheme.colorScheme.onSurface,
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
            .background(MaterialTheme.colorScheme.background)
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
            stringResource(com.example.search.R.string.search_header),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
        )
    }

}