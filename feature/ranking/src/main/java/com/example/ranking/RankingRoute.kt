package com.example.ranking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ranking.navigation.RankingRoute

@Composable
internal fun RankingRoute(){

    RankingScreen()
}

@Composable
fun RankingScreen(){

    Box(){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {  }
        Text(text = "RankingRoute")
    }
}

