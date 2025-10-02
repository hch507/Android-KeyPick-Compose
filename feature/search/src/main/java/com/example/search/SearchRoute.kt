package com.example.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SearchRoute(
    onSearchClick: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchKeyword = viewModel.searchKeyword
    SearchScreen(
        onSearchClick = onSearchClick,
        onSearchKeywordChange = {
            viewModel.onSearchKeywordChanged(it)
        },
        searchKeyword = searchKeyword
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onSearchClick: () -> Unit,
    onSearchKeywordChange: (String) -> Unit,
    searchKeyword: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main") },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    Button(
                        onClick = onSearchClick
                    ) {
                        Text(text = "KeywordInfo")
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "SearchScreen")

            OutlinedTextField(
                value = searchKeyword,
                onValueChange = { newValue -> onSearchKeywordChange(newValue) },
                label = { Text(text = "검색어 입력") },
//            textStyle = TextStyle(
//                fontFamily = neoRegular
//            )
            )
        }
    }

}