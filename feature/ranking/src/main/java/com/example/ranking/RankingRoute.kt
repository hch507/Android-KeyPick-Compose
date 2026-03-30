package com.example.ranking

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.card.AppCard
import com.example.designsystem.textfield.SearchTextField
import com.example.designsystem.theme.rankBackground
import com.keypick.core.model.MyRank
import com.keypick.core.model.Rank

@Composable
internal fun RankingRoute(
    blogId: String,
    viewModel: RankViewModel = hiltViewModel(),
) {

    var rankState = viewModel.rankResult.collectAsStateWithLifecycle()
    RankingScreen(
        onSearchRankCLick = { keyword ->
            viewModel.fetchBlogRankData(keyword, blogId = blogId)
        },
        uiState = rankState.value
    )
}

@Composable
fun RankingScreen(
    onSearchRankCLick: (String) -> Unit,
    uiState: RankUiState<Rank>,
) {

    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            RankingSearchSection(
                onSearchRankCLick = onSearchRankCLick
            )
            when (uiState) {
                RankUiState.Error -> {
                    Log.d("DEBUG_RANK", "-RankingScreen() called ${uiState._data}")
                }

                RankUiState.Loading -> {}
                is RankUiState.Success<Rank> -> {
                    Log.d("DEBUG_RANK", "-RankingScreen() called ${uiState.data.toString()}")
                    RankResultSection(uiState.data)
                }
            }
        }
    }
}


@Composable
fun RankingSearchSection(
    onSearchRankCLick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 32.dp,
                    bottomEnd = 32.dp
                )
            )
            .background(MaterialTheme.colorScheme.rankBackground)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            var keyword by remember { mutableStateOf("") }

            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = stringResource(R.string.ranking_description),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp
            )

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
fun RankResultSection(rank: Rank) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ResultCard(rank.myRank)
        Text(stringResource(R.string.ranking_posting_title), fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 15.dp)
        ) {
            items(
                items = rank.allRank
            ) { item ->

                RankListItem(
                    index = item.rank,
                    title = item.title,
                    link = item.link
                )

                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }

}

@Composable
fun RankListItem(
    index: Int,
    title: String,
    link: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(
            text = index.toString(),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))


        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            LinkText(link)
        }
    }
}

@Composable
fun ResultCard(rank: MyRank) {
    AppCard() {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.ranking_my_rank, rank.myRank),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(text = if(rank.myRank=="+100") {
                stringResource(R.string.ranking_not_in_100)
            } else {
                rank.title
            }, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}
@Composable
fun LinkText(link: String) {
    val context = LocalContext.current

    Text(
        text = link,
        fontSize = 14.sp,
        color = Color.Blue,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.clickable {
            val safeLink = "https://$link"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(safeLink))
            context.startActivity(intent)
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewDisableAppButton() {
    MaterialTheme {
        ResultCard(MyRank(
            myRank = "1",
            title = "test"
        ),)
    }
}