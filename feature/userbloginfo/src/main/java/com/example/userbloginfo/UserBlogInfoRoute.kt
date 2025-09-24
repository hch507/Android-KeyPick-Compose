package com.example.userbloginfo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.KeypickComposeTheme

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
            .padding(horizontal = 30.dp)
    ) {

        LazyColumn {
            item {
                BlogProfile()
            }

            item {
                Spacer(Modifier.height(15.dp))
            }

            item {
                Text("내 블로그", fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            }

            item {
                Text("ddoaak님의 블로그 현황을 알려드릴게요.", fontSize = 15.sp)
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
                        modifier = Modifier.align(Alignment.CenterEnd) // Box 내부 정렬
                    )
                }
            }

            item {
                BlogInfoBody()
            }

            item {
                Spacer(Modifier.height(15.dp))
            }

            item{
                RecomandKeywordCard()
            }
        }
    }
}

@Composable
fun RecomandKeywordCard(){
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 4.dp,
        border = BorderStroke(2.dp, Color.Blue),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "추천 키워드",
                fontSize = 25.sp
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = "오늘의 추천 키워드를 확인해보세요",
                fontSize = 15.sp
            )
            Spacer(Modifier.height(7.dp))
            RecomandButton(
                text = "추천 키워드",
                onClick = {null},
            ){
                Icon(
                    painter = painterResource(id = com.example.designsystem.R.drawable.ic_logout),
                    contentDescription = "Posts",
                    tint = Color(0xFF333366),
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}
@Composable
fun RecomandButton(
    text: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent // 배경 없애서 겹치기 가능하게
            ),
            contentPadding = PaddingValues(0.dp), // 내부 여백 제거
            modifier = Modifier.defaultMinSize(1.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        ) {
            icon()
        }
    }
}
@Composable
fun BlogInfoBody() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            BlogInfoCard(modifier = Modifier.weight(1f), data = "1234", descrption = "오늘 방문자", icon = com.example.designsystem.R.drawable.ic_today_visitor)
            Spacer(Modifier.width(10.dp))
            BlogInfoCard(modifier = Modifier.weight(1f), data = "185+", descrption = "전날 대비", icon = com.example.designsystem.R.drawable.ic_gap)
        }
        Spacer(Modifier.height(10.dp))
        Box(modifier= Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp))

    }

}

@Composable
fun BlogInfoCard(
    modifier: Modifier =Modifier,
    data : String ,
    descrption : String,
    @DrawableRes icon : Int
) {
    Box(
        modifier= modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ){
        Column {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Posts",
                tint = Color(0xFF333366),
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
fun BlogProfile() {
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
        Icon(
            painter = painterResource(id = com.example.designsystem.R.drawable.ic_logout),
            contentDescription = "logout",
            tint = Color(0xFF333366),
            modifier = Modifier.size(20.dp).align(Alignment.TopEnd)
        )
        Row() {
            Box(
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(50)
                    )
                    .align(Alignment.CenterVertically)
            ) {}
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = "ddoaak님의 블로그",
                    fontSize = 20.sp
                )
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = com.example.designsystem.R.drawable.ic_store_nav),
                            contentDescription = "Posts",
                            tint = Color(0xFF333366),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "12",
                            fontSize = 12.sp,
                            color = Color(0xFF333366)
                        )
                    }
                }

            }

        }

    }
}
@Preview(showBackground = true)
@Composable
fun RecomandKeywordCardPreview(){
    KeypickComposeTheme {
        RecomandKeywordCard()
    }
}
@Preview(showBackground = true)
@Composable
fun BlogProfilePreview() {
    KeypickComposeTheme {
        BlogProfile()
    }
}

@Preview(showBackground = true)
@Composable
fun BlogInfoScreenPreview(){
    KeypickComposeTheme {
        UserBlogInfoScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun BlogInfoCardPreview(){
    KeypickComposeTheme {
       BlogInfoCard(
           data = "1234",
           descrption ="오늘 방문자" ,
           icon = com.example.designsystem.R.drawable.ic_store_nav
       )
    }
}