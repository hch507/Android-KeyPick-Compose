package com.example.nonlogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import com.example.nonlogin.navigation.NonLoginRoute
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun NonLoginRoute(
    onMoveToLoginClick: () -> Unit
) {
    NonLoginScreen(
        onMoveToLoginClick = onMoveToLoginClick
    )
}


@Composable
fun NonLoginScreen(
    onMoveToLoginClick: () -> Unit = {}
) {
    Scaffold(

        topBar = {
            KeyPickTopBar("test")
        },

        ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets(0, 0, 0, 0))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.nonlogin_title))
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = onMoveToLoginClick,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.primary

                    ),
                    shape = RoundedCornerShape(15)
                ) {
                    Text(text = stringResource(R.string.nonlogin_register))
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyPickTopBar(
    query: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), // 높이는 기본 텍스트필드에 맞춤
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = {},
                    shape = CircleShape,
                    modifier = Modifier
                        .width(200.dp)
                        .defaultMinSize(minHeight = 30.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    )
                    // 원하는 너비 지정
                )
            }
        }
    )
}