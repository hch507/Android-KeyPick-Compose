package com.example.keywordinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.R
import com.example.designsystem.theme.KeypickComposeTheme
import com.example.keywordinfo.navigation.KeywordInfoLevelDestination
import com.keypick.core.model.KeywordInfo
import kotlinx.coroutines.launch


@Composable
internal fun KeywordInfoRoute(
    viewModel: KeywordInfoViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchkeywordInfoData("아이패드")
    }

    val keywordInfoState by viewModel.keywordInfoState.collectAsStateWithLifecycle()
    KeywordInfoScreen(keywordInfoState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeywordInfoScreen(
    keywordinfoState: KeywordInfoUiState<KeywordInfo>
) {
    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            KeywordInfoTopBar(
                query = "",
                onQueryChange = {},
                onSearchClick = {},
                onBackClick = {}
            )
        },

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            KeywordInfoTabLayout(keywordinfoState = keywordinfoState)
        }
    }

}

@Composable
fun KeywordInfoTabLayout(
    tablist: List<KeywordInfoLevelDestination> = KeywordInfoLevelDestination.entries,
    keywordinfoState: KeywordInfoUiState<KeywordInfo>
) {
    val pagerState = rememberPagerState(pageCount = { tablist.size })
    val coroutineScope = rememberCoroutineScope()
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            selectedTabIndex = pagerState.currentPage
        ) {

            tablist.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier,
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(item.titleText),
                            color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else Color.LightGray
                        )
                    }
                )
            }

        }
        HorizontalPager(
            state = pagerState
        ) { index ->
            when (tablist[index]) {
                KeywordInfoLevelDestination.KEYWORD_DETAIL -> {
                    KeywordDetailRoute()
                }

                KeywordInfoLevelDestination.RELATED_KEYWORDS -> {
                    RelatedKeywordsRoute(keywordinfoState)
                }
            }
        }
    }

}


@Composable
fun KeywordInfoTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier.size(30.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    shape = RoundedCornerShape(20f),
                    color = Color(0xFFE0E0E0)
                )

        ) {
            Row() {
                TextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f),
                    // 배경 제거
                    placeholder = {
                        Text("검색어를 입력해 주세요")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(

                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,

                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )
                )

                // 검색 아이콘
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.ic_search_nav),
                        contentDescription = "Search"
                    )
                }
            }
            // 입력창

        }

    }
}

@Preview(showBackground = true)
@Composable
fun KeywordInfoTopBarPreview() {
    var query by remember { mutableStateOf("") }
    KeypickComposeTheme {
        KeywordInfoTopBar(
            query = query,
            onQueryChange = { query = it },
            onSearchClick = { /* 검색 버튼 클릭 */ },
            onBackClick = { /* 뒤로가기 클릭 */ }
        )
    }

}