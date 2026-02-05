package com.example.userbloginfo

import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.theme.KeypickComposeTheme
import com.example.userbloginfo.chart.BlogVisitorChart
import com.keypick.core.model.UserBlogCntData
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun UserBlogInfoRoute(
    viewModel: UserBlogInfoViewModel = hiltViewModel(),
    onMoveToLogin: () -> Unit,
    onRecommendKeywordSearch: (String) -> Unit
) {
    val blogCntUiState by viewModel.userBlogCntState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.logoutEvent.collectLatest {
            onMoveToLogin()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.recommendEvent.collectLatest { keyword ->
            if (keyword == null) {
                Toast.makeText(
                    context,
                    "추천 키워드가 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                onRecommendKeywordSearch(keyword)
            }
        }
    }
    UserBlogInfoScreen(
        blogCntUiState = blogCntUiState,
        onLogoutClick = {
            viewModel.logout()
        },
        onRecommendClick = { viewModel.getRecommendKeyword() },
    )
}


@Composable
fun UserBlogInfoScreen(
    blogCntUiState: BlogCntUiState<UserBlogCntData>,
    onLogoutClick: () -> Unit,
    onRecommendClick: () -> Unit,

    ) {
    when (blogCntUiState) {
        BlogCntUiState.Error -> {}
        BlogCntUiState.Loading -> {}
        is BlogCntUiState.Success<*> -> {
            blogCntUiState._data?.let {
                UserBlogInfoContent(
                    data = it,
                    onLogoutClick = onLogoutClick,
                    onRecommendClick = onRecommendClick
                )
            }
        }
    }

}

@Composable
fun UserBlogInfoContent(
    data: UserBlogCntData,
    onLogoutClick: () -> Unit,
    onRecommendClick: () -> Unit
) {
    Log.d("UserBlogInfoContent", "UserBlogInfoContent: ${data} ")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {

        LazyColumn {
            item {
                BlogProfile(blogId = data.blogId, onLogoutClick = onLogoutClick)
            }

            item {
                Spacer(Modifier.height(15.dp))
            }

            item {
                Text("내 블로그", fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            }

            item {
                Text("${data.blogId}님의 블로그 현황을 알려드릴게요.", fontSize = 15.sp)
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        "2025.08.08",
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }

            item {
                BlogInfoBody(data)
            }

            item {
                Spacer(Modifier.height(15.dp))
            }

            item {
                RecommendKeywordCard(onRecommendClick)
            }
        }
    }
}

@Composable
fun RecommendKeywordCard(
    onRecommendClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1F1F88),
                        Color(0xFF728AFF)
                    )
                )
            )
            .clickable { onRecommendClick() }

    ) {
        Row(

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = "오늘의 키워드가 \n도착했어요.",
                    fontSize = 25.sp,
                    color = Color.White

                )
                Spacer(Modifier.height(7.dp))
                Text(
                    text = "지금 참여하고 선물을 받으세요.",
                    fontSize = 15.sp,
                    color = Color.White
                )
                Spacer(Modifier.height(7.dp))

            }
        }

    }
}

@Composable
fun BlogInfoBody(
    data: UserBlogCntData,
) {
    val todayCnt = data.visitorCntList[0].cnt
    val gapCnt = data.visitorCntList[0].cnt.toInt() - data.visitorCntList[1].cnt.toInt()
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            BlogInfoCard(
                modifier = Modifier.weight(1f),
                data = todayCnt,
                descrption = "오늘 방문자",
                icon = com.example.designsystem.R.drawable.ic_home_today
            )
            Spacer(Modifier.width(10.dp))
            BlogInfoCard(
                modifier = Modifier.weight(1f),
                data = gapCnt.toString(),
                descrption = "전날 대비",
                icon = com.example.designsystem.R.drawable.ic_home_yesterday
            )
        }
        Spacer(Modifier.height(10.dp))
        Box(
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
            Column() {
                Text(
                    "최근 5일 방문자 분석",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(20.dp))
                BlogVisitorChart(
                    visitors = listOf(data.visitorCntList[4].cnt.toFloat(), data.visitorCntList[3].cnt.toFloat(), data.visitorCntList[2].cnt.toFloat(), data.visitorCntList[1].cnt.toFloat(), data.visitorCntList[0].cnt.toFloat()),
                    labels = listOf("4일 전", "3일 전", "2일 전", "1일 전", "오늘")
                )
            }
        }

    }

}

@Composable
fun BlogInfoCard(
    modifier: Modifier = Modifier,
    data: String,
    descrption: String,
    @DrawableRes icon: Int
) {
    Box(
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Posts",
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = data,
                fontSize = 25.sp
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = descrption,
                fontSize = 15.sp
            )
        }
    }
}


@Composable
fun BlogProfile(
    blogId: String,
    onLogoutClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)


    ) {
        IconButton(
            onClick = onLogoutClick,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = com.example.designsystem.R.drawable.ic_logout),
                contentDescription = "logout",
                tint = Color(0xFF333366),
                modifier = Modifier
                    .size(20.dp)
            )
        }

        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = "${blogId}님의 블로그",
            fontSize = 20.sp
        )


    }
}

@Preview(showBackground = true)
@Composable
fun RecomandKeywordCardPreview() {
    KeypickComposeTheme {
        RecommendKeywordCard(
            onRecommendClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BlogProfilePreview() {
    KeypickComposeTheme {
        BlogProfile("ddoaak", onLogoutClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun BlogInfoScreenPreview() {
    KeypickComposeTheme {
        UserBlogInfoContent(
            data = UserBlogCntData(
                blogId = "test",
                visitorCntList = listOf(

                )
            ),
            onLogoutClick = {},
            onRecommendClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BlogInfoCardPreview() {
    KeypickComposeTheme {
        BlogInfoCard(
            data = "1234",
            descrption = "오늘 방문자",
            icon = com.example.designsystem.R.drawable.ic_store_nav
        )
    }
}