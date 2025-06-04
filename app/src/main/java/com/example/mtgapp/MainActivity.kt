package com.example.mtgapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mtgapp.domain.DeckRepository
import com.example.mtgapp.models.GameMode
import com.example.mtgapp.models.Player
import com.example.mtgapp.screens.*
import com.example.mtgapp.ui.theme.MTGAPPTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

class MainActivity : ComponentActivity() {

    private lateinit var deckRepository: DeckRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deckRepository = DeckRepository(this)
        setContent {
            MTGAPPTheme {
                AppNavigation(deckRepository)
            }
        }
    }
}
//Навигация
@Composable
private fun AppNavigation(deckRepository: DeckRepository) {
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
                navigateToStandard = { navController.navigate("deck-selection/${GameMode.STANDARD}/2") },
                navigateToCommander = { navController.navigate("commander") }
            )
        }

        composable("commander") {
            CommanderScreen(
                onPlayersCountSelected = {
                    navController.navigate("deck-selection/${GameMode.COMMANDER}/$it")
                }
            )
        }

        composable(
            route = "deck-selection/{gameMode}/{playersCount}",
            arguments = listOf(
                navArgument("gameMode") {
                    type = NavType.StringType
                },
                navArgument("playersCount") {
                    type = NavType.IntType
                }
            )
        ) { navBackStack ->
            val argsGameMode = navBackStack.arguments?.getString("gameMode") ?: return@composable
            val argsPlayersCount = navBackStack.arguments?.getInt("playersCount") ?: return@composable
            DeckSelectionScreen(
                playersCount = argsPlayersCount,
                navigateToGame = {
                    val players = URLEncoder.encode(Gson().toJson(it), "UTF-8")
                    navController.navigate("game/$argsGameMode/$players")
                },
                decksFlow = deckRepository.decksFlow,
                onCreateDeckClick = {  }
            )
        }

        composable(
            route = "game/{gameMode}/{players}",
            arguments = listOf(
                navArgument("gameMode") {
                    type = NavType.StringType
                },
                navArgument("players") {
                    type = NavType.StringType
                }
            )
        ) { navBackStack ->
            val argsGameMode = navBackStack.arguments?.getString("gameMode") ?: return@composable
            val argsPlayers = navBackStack.arguments?.getString("players") ?: return@composable
            GameScreen(
                mode = GameMode.valueOf(argsGameMode),
                players = Gson().fromJson(argsPlayers, object : TypeToken<List<Player>>() {}.type)
            )
        }

        composable(route = "deck-create") {
            DecksCreateScreen(
                onSaveClick =
            )
        }
    }
}

