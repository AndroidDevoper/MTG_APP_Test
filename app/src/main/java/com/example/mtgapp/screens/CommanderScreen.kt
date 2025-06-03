package com.example.mtgapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun CommanderScreen(
    onPlayersCountSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { onPlayersCountSelected(3) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("3")
        }

        Button(
            onClick = { onPlayersCountSelected(4) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("4")
        }

        Button(
            onClick = { onPlayersCountSelected(5) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("5")
        }

        Button(
            onClick = { onPlayersCountSelected(6) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("6")
        }
    }
}

@Preview
@Composable
private fun CommanderScreenPreview() {
    MTGAPPTheme {
        CommanderScreen {  }
    }
}