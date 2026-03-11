package com.example.ranking

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.button.AppButton
import com.example.designsystem.textfield.SearchTextField

@Composable
internal fun RankingRoute(
    blogId: String,
    viewModel: RankViewModel = hiltViewModel(),
){

    var rankState = viewModel.rankResult.collectAsStateWithLifecycle()
    RankingScreen(
        onSearchRankCLick = { keyword ->
            viewModel.fetchBlogRankData(keyword,blogId= blogId)
        },
        uiState = rankState.value
    )
}

@Composable
fun RankingScreen(
    onSearchRankCLick: (String ) -> Unit,
    uiState:RankUiState<Int>,
){

    Box(){
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            RankingSearchSection(
                onSearchRankCLick = onSearchRankCLick
            )
            when(uiState){
                RankUiState.Error -> {
                    Log.d("DEBUG_RANK", "-RankingScreen() called ${uiState._data}")
                }
                RankUiState.Loading -> {}
                is RankUiState.Success<*> -> {
                    Log.d("DEBUG_RANK", "-RankingScreen() called ${uiState.data.toString()}")
                    ResultCard(uiState.data.toString())
                }
            }
        }
    }
}


@Composable
fun RankingSearchSection(
    onSearchRankCLick : (String ) -> Unit
){
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
                    onSearchRankCLick(keyword)
                }
            )
        }
    }
}

@Composable
fun ResultCard(rank : String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "내 순위 $rank",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text("베이퍼맥스 신발끈 묶는 방법 공유해요")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisableAppButton(){
    MaterialTheme{
        ResultCard("1")
    }
}