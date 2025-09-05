package com.example.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SearchRoute(
    onSearchClick : () -> Unit
) {

    SearchScreen(onSearchClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onSearchClick : () -> Unit
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
        }
    }

}