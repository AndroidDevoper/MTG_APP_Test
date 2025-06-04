package com.example.mtgapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.mtgapp.R
import com.example.mtgapp.composable.TitleText
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
    onDeleteClicked: (Int) -> Unit
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

    Image(
        painter = painterResource(R.drawable.img_deck_selection),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TitleText(text = "Выберает игрок $currentPlayer")

        Spacer(Modifier.height(12.dp))

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
            onClick = onCreateDeckClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Создать колоду")
        }

        Spacer(Modifier.height(16.dp))

        DecksList(
            decks = decks,
            onSelectDeck = { selectDeck(it) },
            onDeleteClicked = onDeleteClicked
        )
    }
}

@Composable
private fun DecksList(
    decks: List<Deck>,
    onSelectDeck: (Deck) -> Unit,
    onDeleteClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(decks) { item ->
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build()
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clickable { onSelectDeck(item) },
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                color = Color.White,
                                shape = CircleShape
                            )
                            .align(Alignment.TopEnd)
                            .clickable { onDeleteClicked(item.id) }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(12.dp)
                        )
                    }

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = item.name,
                        color = Color.White,
                        fontSize = TextUnit(20f, TextUnitType.Sp)
                    )
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
            ),
            onDeleteClicked = {}
        )
    }
}
