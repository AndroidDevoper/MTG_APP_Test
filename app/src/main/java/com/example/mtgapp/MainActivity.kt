package com.example.mtgapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.SavedState
import com.example.mtgapp.bd.DeckRepository
import com.example.mtgapp.ui.theme.MTGAPPTheme

class MainActivity : ComponentActivity() {

    lateinit var deckRepository: DeckRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deckRepository = DeckRepository(this)
        enableEdgeToEdge()
        setContent {
            MTGAPPTheme {
                AppNavigation(deckRepository)
            }
        }
    }
}
//Навигация
@Composable
fun AppNavigation(deckRepository: DeckRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                navigateToGame = { navController.navigate("select-mode") },
                navigateToDecks = { navController.navigate("decks") },
                navigateToStats = { navController.navigate("stats") }
            )
        }

        composable("select-mode") {
            SelectModeScreen(
                navigateToStandard = { navController.navigate("deck-selection") },
                navigateToCommander = { navController.navigate() }
            )
        }

        composable("deck-selection/{gameType}") {

            DeckSelectionScreen(
                navigateToGame = {},
                decks = deckRepository.getAllDecks()
            )
        }
    }
}

