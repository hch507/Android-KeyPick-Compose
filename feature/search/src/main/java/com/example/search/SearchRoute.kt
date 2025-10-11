package com.example.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SearchRoute(
    onSearchClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchKeyword = viewModel.searchKeyword
    SearchScreen(
        onSearchClick ={
            onSearchClick(searchKeyword)
        } ,
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done // 또는 Search 등 원하는 타입
                ),


                keyboardActions = KeyboardActions(
                    onDone = {
                        onSearchClick()
                    }
                ),
                singleLine = true,
            )
        }
    }

}