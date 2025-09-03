package com.example.keypick_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.designsystem.theme.KeypickComposeTheme
import com.example.keypick_compose.ui.KeyPickApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KeypickComposeTheme {
                KeyPickApp()
            }
        }
    }
}
