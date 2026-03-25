package com.example.userbloginfo

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.card.AppCard
import com.example.designsystem.chart.KeyPickChart
import com.example.designsystem.dialog.LogoutCustomDialog
import com.example.designsystem.theme.KeypickComposeTheme
import com.keypick.core.model.UserBlogCntData
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
internal fun UserBlogInfoRoute(
    viewModel: UserBlogInfoViewModel = hiltViewModel(),
    onMoveToLogin: () -> Unit,
    onRecommendKeywordSearch: (String) -> Unit
) {
    val blogCntUiState by viewModel.userBlogCntState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var showLogoutDialog by remember { mutableStateOf(false) }

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
                    context.getString(R.string.user_info_empty_recommend),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                onRecommendKeywordSearch(keyword)
            }
        }
    }
    if (showLogoutDialog) {
        LogoutCustomDialog (
            onConfirm ={
                showLogoutDialog = false
                viewModel.logout()
            } ,
            onDismiss = { showLogoutDialog = false },

        )
    }
    UserBlogInfoScreen(
        blogCntUiState = blogCntUiState,
        onLogoutClick = {
            showLogoutDialog = true
        },
        onRecommendClick = { viewModel.getRecommendKeyword() },
    )
}


@RequiresApi(Build.VERSION_CODES.O)
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
                Text(stringResource(R.string.user_info_blog), fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            }

            item {
                Text(stringResource(R.string.user_info_blog_description, data.blogId), fontSize = 15.sp)
            }

            item {
                val today = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                    .format(Date())
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        today,
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = stringResource(R.string.user_info_recommend_message),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.surface

                )
                Spacer(Modifier.height(7.dp))
                Text(
                    text = stringResource(R.string.user_info_recommend_description),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.surface
                )
                Spacer(Modifier.height(7.dp))

            }

            Image(
                painter = painterResource(id = com.example.designsystem.R.drawable.ic_home_gift), // 네 이미지로 교체
                contentDescription = "gift",
                modifier = Modifier
                    .height(100.dp)     // 적당한 고정 높이 지정
                    .aspectRatio(1f)  // 크기 조절
            )

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
                description = stringResource(R.string.user_info_today_visitor),
                icon = com.example.designsystem.R.drawable.ic_home_today
            )
            Spacer(Modifier.width(10.dp))
            BlogInfoCard(
                modifier = Modifier.weight(1f),
                data = gapCnt.toString(),
                description = stringResource(R.string.user_info_gap),
                icon = com.example.designsystem.R.drawable.ic_home_yesterday
            )
        }
        Spacer(Modifier.height(10.dp))
        AppCard (
            modifier = Modifier
        ) {
            Column() {
                Text(
                    stringResource(R.string.user_info_chart_title),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(20.dp))
                KeyPickChart(
                    visitors = listOf(data.visitorCntList[4].cnt.toFloat(), data.visitorCntList[3].cnt.toFloat(), data.visitorCntList[2].cnt.toFloat(), data.visitorCntList[1].cnt.toFloat(), data.visitorCntList[0].cnt.toFloat()),
                    labels = listOf(stringResource(R.string.user_info_4_ago),
                        stringResource(R.string.user_info_3_ago),
                        stringResource(R.string.user_info_2_ago),
                        stringResource(R.string.user_info_1_ago),
                        stringResource(R.string.user_info_today)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    showAllLabels = true
                )
            }
        }

    }

}

@Composable
fun BlogInfoCard(
    modifier: Modifier = Modifier,
    data: String,
    description: String,
    @DrawableRes icon: Int
) {
    AppCard(
        modifier = modifier
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
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = description,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
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
                color = MaterialTheme.colorScheme.surface,
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
                modifier = Modifier
                    .size(20.dp)
            )
        }

        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = stringResource(R.string.user_info_title_description, blogId),
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

@RequiresApi(Build.VERSION_CODES.O)
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
            description = "오늘 방문자",
            icon = com.example.designsystem.R.drawable.ic_store_nav
        )
    }
}