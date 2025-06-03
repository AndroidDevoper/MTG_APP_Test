package com.example.mtgapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.mtgapp.composable.CommonButton
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun MainScreen(
    navigateToGame: () -> Unit,
    navigateToDecks: () -> Unit,
    navigateToStats: () -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
        ) {
            CommonButton(
                text = "Начать игру",
                onClick = navigateToGame
            )

            CommonButton(
                text = "Управление колодами",
                onClick = navigateToDecks
            )

            CommonButton(
                text = "Статистика",
                onClick = navigateToStats
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MTGAPPTheme {
        MainScreen({},{},{})
    }
}