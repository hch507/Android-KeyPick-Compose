package com.example.keywordinfo

import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.R
import com.example.designsystem.theme.KeypickComposeTheme
import com.example.keywordinfo.navigation.KeywordInfoLevelDestination
import kotlinx.coroutines.launch


@Composable
internal fun KeywordInfoScreen(
    keyword : String,
    viewModel: KeywordInfoViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchKeywordInfoData(keyword)
    }

    KeywordInfoScreen(
        onSearchClick = { search ->
            viewModel.fetchKeywordInfoData(search)
        },
        viewModel = viewModel
    )
}


@Composable
fun KeywordInfoScreen(

    onSearchClick: (String) -> Unit,
    viewModel : KeywordInfoViewModel
) {
    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            KeywordInfoTopBar(
                onSearchClick = onSearchClick,
                onBackClick = {}
            )
        },

        ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            KeywordInfoTabLayout( viewModel = viewModel)
        }
    }

}

@Composable
fun KeywordInfoTabLayout(
    tabList: List<KeywordInfoLevelDestination> = KeywordInfoLevelDestination.entries,
    viewModel : KeywordInfoViewModel
) {
    val pagerState = rememberPagerState(pageCount = { tabList.size })
    val coroutineScope = rememberCoroutineScope()
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            selectedTabIndex = pagerState.currentPage
        ) {

            tabList.forEachIndexed { index, item ->
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
            when (tabList[index]) {
                KeywordInfoLevelDestination.KEYWORD_DETAIL -> {
                    KeywordDetailRoute(viewModel = viewModel)
                }

                KeywordInfoLevelDestination.RELATED_KEYWORDS -> {
                    RelatedKeywordsRoute(viewModel = viewModel)
                }
            }
        }
    }

}


@Composable
fun KeywordInfoTopBar(
    onSearchClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
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
                    onValueChange = { newValue ->
                        query=newValue
                    },
                    modifier = Modifier
                        .weight(1f),
                    // 배경 제거
                    placeholder = {
                        Text("검색어를 입력해 주세요")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done // 또는 Search 등 원하는 타입
                    ),


                    keyboardActions = KeyboardActions(
                        onDone = {
                            onSearchClick(query)
                        }
                    ),
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
                    onClick = { onSearchClick(query) },
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
    KeypickComposeTheme {
        KeywordInfoTopBar(

            onSearchClick = { /* 검색 버튼 클릭 */ },
            onBackClick = { /* 뒤로가기 클릭 */ }
        )
    }

}