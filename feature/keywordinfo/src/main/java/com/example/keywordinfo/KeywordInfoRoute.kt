package com.example.keywordinfo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.unit.dp

@Composable
internal fun KeywordInfoRoute(){

    KeywordInfoScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeywordInfoScreen(){
    var selectedTabIndex by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("KeywordInfo") },
                colors = TopAppBarDefaults.topAppBarColors(),
            )
        },
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            KeywordInfoTabLayout(
                selectedTabIndex= selectedTabIndex,
                onTabSelected = {selectedTabIndex = it}
            )
        }
    }

}

@Composable
fun KeywordInfoTabLayout(
    selectedTabIndex : Int,
    onTabSelected : (Int) -> Unit
){
    val tabTitles = listOf("키워드 정보", "연관검색어")
    TabRow(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(20.dp)),

        selectedTabIndex = selectedTabIndex
    ) {

        tabTitles.forEachIndexed { index, title ->
            Tab(
                modifier = if(selectedTabIndex == index){
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.primary)
                }else{
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.background)
                },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    }
}