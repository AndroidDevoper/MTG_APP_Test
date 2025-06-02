package com.example.mtgapp

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.models.Deck
import com.example.mtgapp.ui.theme.MTGAPPTheme
import kotlin.random.Random

@Composable
fun DeckSelectionScreen(
    navigateToGame: (Int) -> Unit, // Принимает название выбранной колоды
    decks: List<Deck>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Кнопка случайной колоды
        Button(
            onClick = {
                Random.nextInt(0, decks.size)
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
                    onClick = { navigateToGame(deck.id) },
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
