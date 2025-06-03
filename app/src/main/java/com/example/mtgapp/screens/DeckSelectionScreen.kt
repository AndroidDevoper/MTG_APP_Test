package com.example.mtgapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.models.*
import com.example.mtgapp.ui.theme.MTGAPPTheme
import kotlin.random.Random

@SuppressLint("MutableCollectionMutableState")
@Composable
fun DeckSelectionScreen(
    playersCount: Int,
    decks: List<Deck>,
    navigateToGame: (List<Player>) -> Unit,
) {
    var currentPlayer by remember { mutableIntStateOf(1) }
    val players by remember { mutableStateOf(mutableListOf<Player>()) }

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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Игрок $currentPlayer")

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

        // Список доступных колод
        LazyColumn {
            items(decks) { deck ->
                Button(
                    onClick = { selectDeck(deck) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(deck.name)
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
            decks = listOf(
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
    }
}
