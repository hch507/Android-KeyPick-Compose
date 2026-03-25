package com.example.keywordinfo

import android.util.Log
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.card.AppCard
import com.example.designsystem.chart.KeyPickChart
import com.keypick.core.model.KeywordBlogCountInfo
import com.keypick.core.model.KeywordBlogInfoResource
import com.keypick.core.model.KeywordInfo
import com.keypick.core.model.MonthRatioResource
import com.keypick.core.model.RelKeywordResource


@Composable
fun KeywordDetailRoute(viewModel: KeywordInfoViewModel){
    val keywordInfoState by viewModel.keywordInfoState.collectAsStateWithLifecycle()
    KeywordDetailScreen(keywordInfoState)
}
@Composable
fun KeywordDetailScreen(
    keywordInfoState: KeywordInfoUiState<KeywordInfo>,
){
    when (keywordInfoState) {
        KeywordInfoUiState.Error -> {
            Log.d("keywordinfoState", "RelatedKeywordsScreen: Error")
        }

        KeywordInfoUiState.Loading -> {
            Log.d("keywordinfoState", "RelatedKeywordsScreen: Loading")
        }

        is KeywordInfoUiState.Success<*> -> {
            keywordInfoState._data?.let {
                KeywordInfoScreen(
                    it.keywordBlogInfoResource,
                    it.relKeywordResource,
                    it.monthRatioResource
                )
            }
        }
    }

}

@Composable
fun KeywordInfoScreen(
    keywordBlogInfoResource: KeywordBlogCountInfo,
    relKeywordResource: List<RelKeywordResource>,
    monthRatioResource: MonthRatioResource
){
    val keyword = relKeywordResource[0].relKeyword
    val pcCnt = relKeywordResource[0].monthlyPcQcCnt
    val mobileCnt = relKeywordResource[0].monthlyMobileQcCnt
    val postingCnt =keywordBlogInfoResource.postingCnt

    val period = monthRatioResource.ratioData?.map {
        it.period?:" "
    }
    var rate = monthRatioResource.ratioData?.map {
        it.rate?.toFloat() ?:0f
    }
    Box(

    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                KeywordCard(keyword = keyword)
            }
            item {
                StatRow(
                    leftTitle = stringResource(R.string.keyword_detail_pc_item),
                    leftValue =pcCnt ,
                    leftIcon =  painterResource(com.example.designsystem.R.drawable.ic_pc_item),
                    rightTitle = stringResource(R.string.keyword_detail_mobile_item),
                    rightValue = mobileCnt,
                    rightIcon = painterResource(com.example.designsystem.R.drawable.ic_mobile_item),

                    )
            }
            item {
                StatRow(
                    leftTitle = stringResource(R.string.keyword_detail_blog_item),
                    leftValue = keywordBlogInfoResource.totalCnt.toString(),
                    leftIcon = painterResource(com.example.designsystem.R.drawable.ic_blog_item),
                    rightTitle = stringResource(R.string.keyword_detail_posting_item),
                    rightValue = if (postingCnt == 100) "+$postingCnt" else "$postingCnt" ,
                    rightIcon = painterResource(com.example.designsystem.R.drawable.ic_posting_tiem)
                )
            }
            item {
                AppCard (
                    modifier = Modifier
                ) {
                    Column() {
                        Text(
                            stringResource(R.string.keyword_detail_chart_title),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        if (rate != null && period != null) {
                            KeyPickChart(
                                visitors = rate,
                                labels = period,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                showAllLabels = false
                            )
                        }
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.keyword_detail_keyword_name),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                text = stringResource(R.string.keyword_detail_item_description),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp
            )
        }
    }
}