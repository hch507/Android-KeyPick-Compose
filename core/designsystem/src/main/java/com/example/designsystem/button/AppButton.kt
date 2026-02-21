package com.example.designsystem.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    text : String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color(0xFF5B6FEF) else Color(0xFFE3E5EC),
            contentColor = if (enabled) Color.White else Color(0xFF5B6FEF),
            disabledContainerColor = Color(0xFFE3E5EC),
            disabledContentColor = Color(0xFF5B6FEF)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (enabled) 4.dp else 0.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDisableAppButton(){
    MaterialTheme{
        AppButton(
            text = "로그인",
            modifier = Modifier,
            enabled = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAbleAppButton(){
    MaterialTheme{
        AppButton(
            text = "로그인",
            modifier = Modifier,
            enabled = true,
            onClick = {}
        )
    }
}

