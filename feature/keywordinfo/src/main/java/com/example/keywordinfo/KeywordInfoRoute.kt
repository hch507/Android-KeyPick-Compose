package com.example.keywordinfo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.keywordinfo.navigation.KeywordInfoLevelDestination
import kotlinx.coroutines.launch

@Composable
internal fun KeywordInfoRoute(
    viewModel: KeywordInfoViewModel = hiltViewModel()
) {
    viewModel.fetchkeywordInfoData("아이패드")

    val pagerState = rememberPagerState(pageCount = {
        2
    })
    val coroutineScope = rememberCoroutineScope()
    KeywordInfoScreen(
        onTabSelected = { index -> coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        } },
        pagerState= pagerState
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeywordInfoScreen(

    onTabSelected: (Int) -> Unit,
    pagerState : PagerState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("KeywordInfo") },
                colors = TopAppBarDefaults.topAppBarColors(),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            KeywordInfoTabLayout(

                onTabSelected =onTabSelected,
                pagerState = pagerState
            )
        }
    }

}

@Composable
fun KeywordInfoTabLayout(
    tablist : List<KeywordInfoLevelDestination> = KeywordInfoLevelDestination.entries,
    onTabSelected: (Int) -> Unit,
    pagerState : PagerState
) {
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(20.dp)
                ),

            selectedTabIndex = pagerState.currentPage
        ) {

            tablist.forEachIndexed { index, item ->
                Tab(
                    modifier = if (pagerState.currentPage == index) {
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    } else {
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.background)
                    },
                    selected = pagerState.currentPage == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = stringResource(item.titleText),
                            color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
                        )
                    }
                )
            }

        }
        HorizontalPager(
            state = pagerState
        ) {index ->
            when(tablist[index]){
                KeywordInfoLevelDestination.KEYWORD_DETAIL->{
                    KeywordDetailRoute()
                }
                KeywordInfoLevelDestination.RELETED_KEYWORDS->{
                    ReletedKeywordsRoute()
                }
            }
        }
    }

}