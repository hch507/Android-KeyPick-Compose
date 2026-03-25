package com.example.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
//TODO : 현재는 디크모드 지원
//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)

private val LightColorScheme = lightColorScheme(
    primary = Primary500,
    onPrimary = White,

    secondary = Black600,
    onSecondary = White,

    surface = White,
    onSurface = Black600,
    onSurfaceVariant = Black900,

    background = Background,
    onBackground = Black,

    error = ErrorRed
)

@Composable
fun KeypickComposeTheme(

    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}