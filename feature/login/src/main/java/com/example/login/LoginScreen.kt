package com.example.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.example.designsystem.button.AppButton
import com.example.designsystem.card.AppCard


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
        onNonLoginClick = onNonLoginClick,
        loginUiState = loginUiState
    )
    if (loginUiState is LoginUiState.Loading){
        LoadingOverlay()
    }


}

@Composable
fun LoginScreen(
    onLoginClick: (String) -> Unit = {},
    onNonLoginClick: () -> Unit = {},
    loginUiState : LoginUiState<*>
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
                modifier = Modifier.fillMaxWidth(),
                isLoginError = loginUiState is LoginUiState.Error
            )
            OrDivider()
            NonLoginButton(
                text = stringResource(R.string.login_move_to_search),
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = onNonLoginClick,
            )
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
    modifier: Modifier,
    isLoginError : Boolean
) {
    AppCard(
        modifier = modifier
    ) {
        Column(modifier = modifier) {
            var blogId by remember { mutableStateOf("") }

            Text(
                text = stringResource(R.string.login_id_title),
                fontSize = 20.sp,
                color = Color.Black
            )

            OutlinedTextField(
                modifier = modifier,
                value = blogId,
                onValueChange = { blogId = it },
                label = { Text(text = stringResource(R.string.login_id_hint)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isLoginError) Color.Red else Color(0xFF5B6FEF),
                    focusedLabelColor = if (isLoginError) Color.Red else Color(0xFF5B6FEF),
                    cursorColor = if (isLoginError) Color.Red else Color(0xFF5B6FEF),
                    focusedSupportingTextColor = Color.Red,
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red
                )
            )
            if (isLoginError) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.login_error_message),
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            AppButton(
                text = stringResource(R.string.login_confirm),
                modifier = Modifier,
                enabled = blogId.isNotBlank(),
                onClick = { onLoginClick(blogId) }
            )
        }
    }

}

@Composable
fun OrDivider(
    modifier: Modifier = Modifier,

    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.LightGray
        )

        Text(
            text = stringResource(R.string.login_divider),
            modifier = Modifier.padding(horizontal = 12.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )

        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.LightGray
        )
    }
}

@Composable
fun NonLoginButton(
    text : String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =  Color(0xFF717379),
            contentColor =  Color.White ,
            disabledContainerColor = Color(0xFFE3E5EC),
            disabledContentColor = Color(0xFF5B6FEF)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }

}


@Composable
fun LoginTitle() {
    Text(
        text = stringResource(R.string.common_app_name),
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
            onNonLoginClick = {},
            loginUiState = LoginUiState.Error
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginSection() {
    MaterialTheme {
        LoginSection(
            onLoginClick = {},
            modifier = Modifier,
            isLoginError = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNonLoginButton(){
    MaterialTheme{
        NonLoginButton(
            text = stringResource(R.string.login_move_to_search),
            modifier = Modifier,
            onClick = {}
        )
    }
}



