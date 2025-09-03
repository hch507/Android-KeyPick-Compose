package com.example.nonlogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import com.example.nonlogin.navigation.NonLoginRoute
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

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
    Box(
        modifier = Modifier
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