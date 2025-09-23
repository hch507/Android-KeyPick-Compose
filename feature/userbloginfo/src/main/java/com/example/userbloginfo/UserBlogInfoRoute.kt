package com.example.userbloginfo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
            .padding(30.dp)
    ) {

        Column {
            BlogProfile()

            Spacer(Modifier.height(15.dp))

            Text("내 블로그", fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            Text("ddoaak님의 블로그 현황을 알려드릴게요.", fontSize = 15.sp)
            Text(
                "2025.08.08",
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(10.dp)
            )
            BlogInfoBody()
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