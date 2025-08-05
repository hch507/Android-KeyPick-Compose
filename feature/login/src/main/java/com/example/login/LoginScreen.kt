package com.example.login

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
internal fun LoginRoute(){
//    val blogId = loginViewModel.blogId
    LoginScreen()
}

@Composable
fun LoginScreen(
//    blogId : String
) {
    Box(
        modifier = Modifier
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(180.dp))
            LoginTitle()
            Spacer(modifier = Modifier.height(60.dp))
            LoginBody(
                blogId = "test", onBlogIdChange = { null }
            )
            Spacer(modifier = Modifier.height(30.dp))
            LoginBottom(
                onLoginClick = {

                },
                onNonLoginClick = {

                })
        }
    }
}


@Composable
fun LoginBottom(
    onLoginClick: () -> Unit, onNonLoginClick: () -> Unit
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
            onValueChange = onBlogIdChange,
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
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewLoginScreen(){
    MaterialTheme {
        LoginScreen()
    }
}


