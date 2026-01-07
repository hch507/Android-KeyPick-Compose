package com.example.keywordinfo

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.theme.KeypickComposeTheme
import com.keypick.core.model.KeywordInfo
import com.keypick.core.model.RelKewordResource

@Composable
fun RelatedKeywordsRoute(
    viewModel: KeywordInfoViewModel
) {
    val keywordInfoState by viewModel.keywordInfoState.collectAsStateWithLifecycle()
    RelatedKeywordsScreen(keywordInfoState, onSaveClick = {
        viewModel.saveKeyword(it)
    })
}

@Composable
fun RelatedKeywordsScreen(
    keywordInfoState: KeywordInfoUiState<KeywordInfo>,
    onSaveClick: (String) -> Unit
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
                KeywordInfoSuccessScreen(
                    it.relKewordResource,
                    onSaveClick
                )
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KeywordInfoSuccessScreen(
    relKeywordResourceList: List<RelKewordResource>,
    onSaveClick: (String) -> Unit
) {
    if (relKeywordResourceList.isEmpty()) return
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 15.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp) // 아이템 간 간격
        ) {

            item { KeywordTitle(relKeywordResourceList[0]) }
            item { Spacer(modifier = Modifier.height(25.dp)) }
            stickyHeader {
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                        .padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
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
fun KeywordTitle(
    relKeywordResource: RelKewordResource,
) {
    RelatedItemBox {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                RelatedItemTitle()
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${relKeywordResource.monthlyPcQcCnt + relKeywordResource.monthlyMobileQcCnt}회")
            }
            Spacer(Modifier.height(10.dp))
            Text(text = relKeywordResource.relKeyword, fontSize = 20.sp)
        }

    }
}

@Composable
fun RelatedItem(
    relKeywordResource: RelKewordResource,
    onSaveClick: (String) -> Unit
) {
    RelatedItemBox() {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            RelatedItemTitle()
            Spacer(Modifier.height(10.dp))
            Text(text = relKeywordResource.relKeyword, fontSize = 20.sp)
            Spacer(Modifier.height(10.dp))
            Text(
                text = relKeywordResource.monthlyPcQcCnt + relKeywordResource.monthlyMobileQcCnt,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.End) // 👈 이 한 줄로 오른쪽 정렬됨
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = { onSaveClick(relKeywordResource.relKeyword) }
            ) {
                Text("Save")
            }

        }
    }
}

@Composable
fun RelatedItemBox(
    modifier: Modifier = Modifier.padding(horizontal = 15.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun RelatedItemTitle(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.Blue,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(vertical = 3.dp, horizontal = 10.dp)
    ) {
        Text(text = "키워드 명", fontSize = 15.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun RelatedItemBoxPreview() {
    KeypickComposeTheme {
        RelatedItemBox {
            Text(text = "연관 키워드 아이템")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RelatedItemTitlePreview() {
    KeypickComposeTheme {
        RelatedItemTitle()
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



