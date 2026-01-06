package com.example.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.CircularProgressIndicator


@Composable
internal fun LoginRoute(
    moveToMain: () -> Unit,
    onNonLoginClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState by viewModel.blogIdResult.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            moveToMain()
        }
    }
    LoginScreen(
        onLoginClick = { blogId ->
            viewModel.checkBlogIdExists(blogId)
        },
        onNonLoginClick = onNonLoginClick
    )
    when (loginUiState) {
        is LoginUiState.Loading -> {
            LoadingOverlay()
        }

        is LoginUiState.Error -> {
            Log.d("test_LoginUiState", "LoginScreen: Error")
        }

        is LoginUiState.Success -> {
            Log.d("test_LoginUiState", "LoginScreen: Success")

        }
    }


}

@Composable
fun LoginScreen(
    onLoginClick: (String) -> Unit = {},
    onNonLoginClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F3))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginTitle()
            Spacer(modifier = Modifier.height(40.dp))
            LoginSection(
                onLoginClick = onLoginClick,
            )
            Spacer(modifier = Modifier.height(40.dp))
            NonLoginSection(onNonLoginClick)
        }
    }


}
@Composable
fun LoadingOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun LoginSection(
    onLoginClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Column(modifier = Modifier) {
            var blogId by remember { mutableStateOf("") }
            Text(
//                text = stringResource(R.string.common_app_name),
                text = "블로그 ID",
                fontSize = 12.sp,
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = blogId,
                onValueChange = { blogId = it },
                label = { Text(text = stringResource(R.string.login_id_hint)) }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onLoginClick(blogId) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15)
            ) {
                Text(text = stringResource(R.string.login_register))
            }
        }
    }

}

@Composable
fun NonLoginSection(
    onNonLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "키워드 검색",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "블로그 없이 검색만 가능해요.",
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNonLoginClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15)
            ) {
                Text(text = stringResource(R.string.login_register))
            }
        }

    }
}

@Composable
fun LoginTitle() {
    Text(
        text = "KEYPICK",
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen(
            onLoginClick = {},
            onNonLoginClick = {}
        )
    }
}


