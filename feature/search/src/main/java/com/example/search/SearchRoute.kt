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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SearchRoute(
    onSearchClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchKeyword = viewModel.searchKeyword
    SearchScreen(
        onSearchClick = {
            onSearchClick(searchKeyword)
        },
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
                title = { Text("검색") },
                colors = TopAppBarDefaults.topAppBarColors(),
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7))
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = searchKeyword,
                    onValueChange = { newValue -> onSearchKeywordChange(newValue) },
                    placeholder = { Text("검색어를 입력해주세요.") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,

                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,

                        cursorColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "search")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(26.dp),
                            clip = false   // ⭐ 중요: 그림자 안 잘리게
                        ),

                    shape = RoundedCornerShape(26.dp),
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
                Spacer(modifier = Modifier.height(12.dp))
                RecentSearchSection()
            }
        }
    }

}

@Composable
fun RecentSearchSection(
){
    val recentSearches = remember {
        mutableStateListOf(
            "블로그 상위 노출",
            "온라인 마케팅 창출",
            "블로그 수익 키워드"
        )
    }
    Column (
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
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    recentSearches.clear()
                }
            )
        }
        recentSearches.forEach { keyword ->
            RecentSearchItem(
                text = keyword,
                onRemove = {
                    recentSearches.remove(keyword)
                }
            )
        }
    }

}
@Composable
fun RecentSearchItem(
    text: String,
    onRemove: () -> Unit
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
                .clickable { onRemove() },
            tint = Color.Gray
        )
    }
}