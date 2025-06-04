package com.example.mtgapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.R
import com.example.mtgapp.composable.TitleText
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun CommanderScreen(
    onPlayersCountSelected: (Int) -> Unit
) {
    Box {
        Image(
            painter = painterResource(R.drawable.img_select_count_players),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
        ) {
            TitleText(
                text = "Выберите колличество игроков",
            )

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
}

@Preview
@Composable
private fun CommanderScreenPreview() {
    MTGAPPTheme {
        CommanderScreen {  }
    }
}