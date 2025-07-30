package com.example.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier


@Composable
internal fun LoginRoute(){
    LoginScreen()
}

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
    ){
        Column {
            Text(text = "로그인 화면")
        }
    }
}


