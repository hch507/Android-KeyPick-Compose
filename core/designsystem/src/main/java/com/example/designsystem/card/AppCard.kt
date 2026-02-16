package com.example.designsystem.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppCard(
    modifier: Modifier,
    content : @Composable () -> Unit
){
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            content
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AppCardPreview() {
    AppCard(modifier = Modifier.size(200.dp, 120.dp)) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Title", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Subtitle", style = MaterialTheme.typography.bodyMedium)
        }
    }
}