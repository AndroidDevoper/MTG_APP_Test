package com.example.mtgapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.models.*
import com.example.mtgapp.ui.theme.MTGAPPTheme
import kotlinx.coroutines.flow.*
import kotlin.random.Random

@SuppressLint("MutableCollectionMutableState")
@Composable
fun DeckSelectionScreen(
    playersCount: Int,
    decksFlow: Flow<List<Deck>>,
    navigateToGame: (List<Player>) -> Unit,
    onCreateDeckClick: () -> Unit,
) {
    var currentPlayer by remember { mutableIntStateOf(1) }

    val players by remember { mutableStateOf(mutableListOf<Player>()) }
    val decks by decksFlow.collectAsState(listOf())

    fun selectDeck(deck: Deck) {
        players.add(
            Player(
                id = players.size + 1,
                deck = deck
            )
        )
        if (players.size >= playersCount) {
            navigateToGame(players)
        } else {
            currentPlayer += 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = "Выберает игрок $currentPlayer",
            textAlign = TextAlign.Center
        )

        // Кнопка случайной колоды
        Button(
            onClick = {
                val deck = decks[Random.nextInt(1, decks.size)]
                selectDeck(deck)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Случайная колода")
        }

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Создать колоду")
        }

        Spacer(Modifier.height(16.dp))

        DecksList(decks)
    }
}

@Composable
private fun DecksList(
    decks: List<Deck>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(decks) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.name)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeckSelectionScreenPreview() {
    MTGAPPTheme {
        DeckSelectionScreen(
            playersCount = 2,
            navigateToGame = {},
            onCreateDeckClick = {},
            decksFlow = flowOf(
                listOf(
                    Deck(
                        id = 1,
                        name = "Колода 1",
                        imageUrl = ""
                    ),
                    Deck(
                        id = 2,
                        name = "Колода 2",
                        imageUrl = ""
                    ),
                    Deck(
                        id = 3,
                        name = "Колода 3",
                        imageUrl = ""
                    )
                )
            )
        )
    }
}
