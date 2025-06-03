package com.example.mtgapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.mtgapp.R
import com.example.mtgapp.models.*
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun GameScreen(
    mode: GameMode,
    players: List<Player>
) {
    when (mode) {
        GameMode.STANDARD -> GameStandardScreen(players)
        GameMode.COMMANDER -> GameCommandScreen(players)
    }
}

@Composable
fun GameStandardScreen(players: List<Player>) {
    val columnCount = (players.size.plus(1)).div(2)
    Row(modifier = Modifier.fillMaxSize()) {
        repeat(columnCount) { rowIndex ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                PlayerSector(
                    modifier = Modifier.weight(1f),
                    mode = GameMode.STANDARD,
                    player = players.getOrNull(rowIndex.times(2))
                )

                PlayerSector(
                    modifier = Modifier.weight(1f),
                    mode = GameMode.STANDARD,
                    player = players.getOrNull(rowIndex.times(2).plus(1))
                )
            }
        }
    }
}

@Composable
fun GameCommandScreen(players: List<Player>) {
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
                    modifier = Modifier.weight(1f),
                    mode = GameMode.COMMANDER,
                    player = players.getOrNull(rowIndex.times(2))
                )

                PlayerSector(
                    modifier = Modifier.weight(1f),
                    mode = GameMode.COMMANDER,
                    player = players.getOrNull(rowIndex.times(2).plus(1))
                )
            }
        }
    }
}

@Composable
private fun PlayerSector(
    modifier: Modifier,
    mode: GameMode,
    player: Player?
) {
    var cardCount by remember { mutableIntStateOf(mode.cardCount) }
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
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
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { cardCount += 1 }
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp)
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.ic_plus),
                        tint = Color.White,
                        contentDescription = null,
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = TextUnit(32f, TextUnitType.Sp),
                            fontWeight = FontWeight.Bold,
                            text = "$cardCount",
                            color = Color.White,
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = player.deck.name,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { cardCount -= 1 }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp)
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.ic_minus),
                        tint = Color.White,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GameScreen2Preview() {
    MTGAPPTheme {
        GameScreen(
            mode = GameMode.STANDARD,
            players = previewPlayers(2)
        )
    }
}

@Preview
@Composable
private fun GameScreen3Preview() {
    MTGAPPTheme {
        GameScreen(
            mode = GameMode.COMMANDER,
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