package com.example.login

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun LoginRoute(
    moveToMain: () -> Unit,
    onNonLoginClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val blogId = viewModel.blogId
    val loginUiState by viewModel.blogIdResult.collectAsStateWithLifecycle()

    LoginScreen(
        blogId = blogId,
        onLoginClick = {
            viewModel.checkBlogIdExists(blogId)

        },
        onNonLoginClick = onNonLoginClick,
        onBlogIdChanged = {
            viewModel.onBlogIdChanged(it)
        },
        moveToMain = moveToMain,
        loginUiState = loginUiState
    )
}

@Composable
fun LoginScreen(
    blogId: String,
    loginUiState: LoginUiState<Boolean>,
    onLoginClick: () -> Unit = {},
    onNonLoginClick: () -> Unit = {},
    onBlogIdChanged: (String) -> Unit,
    moveToMain: () -> Unit = {}
) {
    Box(
        modifier = Modifier
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(180.dp))
            LoginTitle()
            Spacer(modifier = Modifier.height(60.dp))
            LoginBody(
                blogId = blogId, onBlogIdChange = onBlogIdChanged
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginBottom(
                onLoginClick = onLoginClick,
                onNonLoginClick = onNonLoginClick,
                moveToMain = moveToMain
            )
        }
    }

    when (loginUiState) {
        is LoginUiState.Loading -> {
            Log.d("test_LoginUiState", "LoginScreen: Loading")
        }
        is LoginUiState.Error -> {
            Log.d("test_LoginUiState", "LoginScreen: Error")
        }
        is LoginUiState.Success -> {
            Log.d("test_LoginUiState", "LoginScreen: Success")
            LaunchedEffect(loginUiState) {
                moveToMain()
            }
        }
    }
}


@Composable
fun LoginBottom(
    onLoginClick: () -> Unit,
    onNonLoginClick: () -> Unit,
    moveToMain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(15)
        ) {
            Text(text = stringResource(R.string.login_register))
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = onNonLoginClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(15)
        ) {
            Text(text = stringResource(R.string.login_non_regsiter))
        }
    }
}

@Composable
fun LoginBody(blogId: String, onBlogIdChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(40.dp)) {
        OutlinedTextField(
            value = blogId,
            onValueChange = { newValue -> onBlogIdChange(newValue) },
            label = { Text(text = stringResource(R.string.login_id_hint)) },
//            textStyle = TextStyle(
//                fontFamily = neoRegular
//            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.login_id_helper),
//            fontFamily = neoRegular,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun LoginTitle() {
    Text(
        modifier = Modifier.padding(start = 30.dp),
        text = stringResource(id = R.string.login_title),
        fontSize = 50.sp,
        color = MaterialTheme.colorScheme.onSurface,
//        fontFamily = neoBold
    )
    Text(
        modifier = Modifier.padding(top = 15.dp, start = 30.dp),
        text = stringResource(id = R.string.login_sub_title),
//        fontFamily = neoRegular
    )
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewLoginScreen(){
//    MaterialTheme {
//        LoginScreen(
//            onLoginClick = {},
//            onNonLoginClick = {}
//        )
//    }
//}


