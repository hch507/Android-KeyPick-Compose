package com.example.keywordinfo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.card.AppCard
import com.keypick.core.model.KeywordInfo
import com.keypick.core.model.RelKeywordResource
import com.keypick.core.model.SavedKeyword


@Composable
fun RelatedKeywordsRoute(
    viewModel: KeywordInfoViewModel
) {
    val context = LocalContext.current
    val keywordInfoState by viewModel.keywordInfoState.collectAsStateWithLifecycle()
    RelatedKeywordsScreen(keywordInfoState, onSaveClick = {
        viewModel.saveKeyword(it)
        Toast.makeText(
            context,
            "저장되었습니다",
            Toast.LENGTH_SHORT
        ).show()
    })
}

@Composable
fun RelatedKeywordsScreen(
    keywordInfoState: KeywordInfoUiState<KeywordInfo>,
    onSaveClick: (SavedKeyword) -> Unit
) {
    when (keywordInfoState) {
        KeywordInfoUiState.Error -> {
            Log.d("keywordinfoState", "RelatedKeywordsScreen: Error")
        }

        KeywordInfoUiState.Loading -> {
            Log.d("keywordinfoState", "RelatedKeywordsScreen: Loading")
        }

        is KeywordInfoUiState.Success<*> -> {
            keywordInfoState._data?.let {
                RelKeywordInfoSuccessScreen(
                    it.relKeywordResource,
                    onSaveClick
                )
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RelKeywordInfoSuccessScreen(
    relKeywordResourceList: List<RelKeywordResource>,
    onSaveClick: (SavedKeyword) -> Unit
) {
    if (relKeywordResourceList.isEmpty()) return
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6FB)),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp) // 아이템 간 간격
        ) {

            item { RelKeywordCard(relKeywordResourceList[0], onSaveClick = onSaveClick) }
            item { Spacer(modifier = Modifier.height(25.dp)) }
            stickyHeader {
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFF4F6FB)
                        )
                        .padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Text(text = "연관 키워드", fontSize = 15.sp, modifier = Modifier.weight(1f))
                        Text(text = "${relKeywordResourceList.size - 1}개", fontSize = 15.sp)
                    }
                }

            }
            items(relKeywordResourceList.size - 1) { index ->
                val item = relKeywordResourceList[index + 1]
                RelatedItem(item, onSaveClick = onSaveClick)
            }
        }
    }
}


@Composable
fun RelKeywordCard(
    keyword: RelKeywordResource,
    onSaveClick: (SavedKeyword) -> Unit
) {
    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            // 제목 + 아이콘
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "키워드 명",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                IconButton(
                    onClick = {
                        onSaveClick(SavedKeyword(
                            keyword= keyword.relKeyword,
                            timestamp =System.currentTimeMillis(),
                            resultCount = keyword.monthlyPcQcCnt + keyword.monthlyMobileQcCnt
                        ))
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    Icon(
                        painter = painterResource(com.example.designsystem.R.drawable.ic_book_mark),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = keyword.relKeyword,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun RelatedItem(
    relKeywordResource: RelKeywordResource,
    onSaveClick: (SavedKeyword) -> Unit
) {
    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // 제목 + 아이콘
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = relKeywordResource.relKeyword, fontSize = 20.sp)
                IconButton(
                    onClick = {
                        onSaveClick(
                            SavedKeyword(
                                keyword = relKeywordResource.relKeyword,
                                timestamp =System.currentTimeMillis(),
                                resultCount = relKeywordResource.monthlyPcQcCnt + relKeywordResource.monthlyMobileQcCnt
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    Icon(
                        painter = painterResource(com.example.designsystem.R.drawable.ic_book_mark),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(com.example.designsystem.R.drawable.ic_releted_search),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "${relKeywordResource.monthlyPcQcCnt + relKeywordResource.monthlyMobileQcCnt}회",
                    color = Color.Gray,
                    fontSize = 15.sp,
                )

            }

        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun RelatedItemPreview() {
//    KeypickComposeTheme {
//        RelatedItem()
//    }
//
//}


//@Preview(showBackground = true)
//@Composable
//fun RelatedKeywordsScreenPreview() {
//    KeypickComposeTheme {
//        RelatedKeywordsScreen()
//    }
//
//}



