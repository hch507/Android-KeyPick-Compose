package com.example.keywordinfo

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.card.AppCard
import com.example.designsystem.chart.KeyPickChart


@Composable
fun KeywordDetailRoute(viewModel: KeywordInfoViewModel){
    val keywordInfoState by viewModel.keywordInfoState.collectAsStateWithLifecycle()
    KeywordDetailScreen()
}
@Composable
fun KeywordDetailScreen(
){

    Box(

    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6FB)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                KeywordCard(keyword = "베이퍼 맥스")
            }
            item {
                StatRow(
                    leftTitle = "PC 검색",
                    leftValue = "4241",
                    leftIcon =  painterResource(com.example.designsystem.R.drawable.ic_pc_item),
                    rightTitle = "모바일 검색",
                    rightValue = "4135",
                    rightIcon = painterResource(com.example.designsystem.R.drawable.ic_mobile_item),

                )
            }
            item {
                StatRow(
                    leftTitle = "블로그",
                    leftValue = "14856121",
                    leftIcon = painterResource(com.example.designsystem.R.drawable.ic_blog_item),
                    rightTitle = "포스팅",
                    rightValue = "855",
                    rightIcon = painterResource(com.example.designsystem.R.drawable.ic_posting_tiem)
                )
            }
            item {
                AppCard (
                    modifier = Modifier
                ) {
                    Column() {
                        Text(
                            "최근 5일 방문자 분석",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        KeyPickChart(
                            visitors = listOf(10.0f,10.0f,10.0f,10.0f,10.0f,),
                            labels = listOf("4일 전", "3일 전", "2일 전", "1일 전", "오늘"),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun KeywordCard(keyword: String){
    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            // 제목 + 아이콘
            Row(
                modifier = Modifier.fillMaxWidth().height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "키워드 명",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Icon(
                    painter = painterResource(com.example.designsystem.R.drawable.ic_book_mark),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .fillMaxHeight()   // 높이 1/3
                        .aspectRatio(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = keyword,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun StatRow(
    leftTitle: String,
    leftValue: String,
    leftIcon : Painter,
    rightTitle: String,
    rightValue: String,
    rightIcon : Painter
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        StatCard(
            title = leftTitle,
            value = leftValue,
            icon = leftIcon,
            modifier = Modifier.weight(1f)
        )

        StatCard(
            title = rightTitle,
            value = rightValue,
            icon = rightIcon,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    AppCard(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // 제목 + 아이콘
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "30일 기준",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}