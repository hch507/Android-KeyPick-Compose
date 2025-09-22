package com.example.userbloginfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun UserBlogInfoRoute(

) {
    UserBlogInfoScreen()
}


@Composable
fun UserBlogInfoScreen(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column {
            Text("내 블로그", fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            Text("ddoaak님의 블로그 현황을 알려드릴게요.", fontSize = 10.sp)
            Text(
                "2025.08.08",
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(10.dp)
            )
        }
    }
}