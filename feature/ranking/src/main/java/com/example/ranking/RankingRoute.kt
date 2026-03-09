package com.example.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.textfield.SearchTextField
import com.example.ranking.navigation.RankingRoute

@Composable
internal fun RankingRoute(
    blogId: String,
    viewModel: RankViewModel = hiltViewModel()
){
    viewModel.fetchBlogRankData("아이패드",blogId= blogId)
    RankingScreen()
}

@Composable
fun RankingScreen(){

    Box(){
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            RankingSearchSection()

        }
    }
}


@Composable
fun RankingSearchSection(){
    Box(
        modifier = Modifier

            .clip(
                RoundedCornerShape(
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
            )
            .background(Color(0xFFC5C9FE))
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            var keyword by remember { mutableStateOf("") }

            Text(modifier = Modifier.padding(bottom = 20.dp), text = stringResource(R.string.ranking_description), color = Color.White, fontSize = 20.sp)

            SearchTextField(
                value = keyword,
                onValueChange = { keyword = it },
                onSearch = {
                    // 검색 실행
                }
            )
        }
    }

}
