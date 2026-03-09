package com.example.designsystem.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "검색어를 입력해주세요.",
    onSearch: (() -> Unit)? = null,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },

        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "search")
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,

            cursorColor = Color.Black
        ),

        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(26.dp),
                clip = false
            ),

        shape = RoundedCornerShape(26.dp),

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),

        keyboardActions = KeyboardActions(
            onSearch = { onSearch?.invoke() }
        ),

        singleLine = true,
    )
}