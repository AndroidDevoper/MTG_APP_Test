package com.example.mtgapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.models.*
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun GameScreen(
    mode: GameMode,
    players: List<Player>
) {
    val rowCount = (players.size.plus(1)).div(2)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(rowCount) { rowIndex ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                PlayerSector(
                    mode = mode,
                    player = players.getOrNull(rowIndex.times(2))
                )

                PlayerSector(
                    mode = mode,
                    player = players.getOrNull(rowIndex.times(2).plus(1))
                )
            }
        }
    }
}

@Composable
private fun RowScope.PlayerSector(
    mode: GameMode,
    player: Player?
) {
    var cardCount by remember { mutableIntStateOf(mode.cardCount) }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = if (player == null)
                Color.Black
            else
                Color.Red
        )
    ) {
        player?.let {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.clickable {
                        cardCount += 1
                    },
                    text = "+",
                    color = Color.White,
                )

                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = "$cardCount",
                    color = Color.White,
                )

                Text(
                    modifier = Modifier.clickable {
                        cardCount -= 1
                    },
                    text = "-",
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GameScreen3Preview() {
    MTGAPPTheme {
        GameScreen(
            mode = GameMode.STANDARD,
            players = previewPlayers(3)
        )
    }
}

@Preview
@Composable
private fun GameScreen6Preview() {
    MTGAPPTheme {
        GameScreen(
            mode = GameMode.COMMANDER,
            players = previewPlayers(6)
        )
    }
}

private fun previewPlayers(count: Int) = mutableListOf<Player>().apply {
    repeat(count) {
        add(
            Player(
                id = it,
                deck = Deck(
                    id = it,
                    name = "Колода $it",
                    imageUrl = ""
                )
            )
        )
    }
}