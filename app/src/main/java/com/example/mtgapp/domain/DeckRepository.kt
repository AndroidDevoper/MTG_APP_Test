package com.example.mtgapp.domain


import android.content.Context
import android.content.SharedPreferences
import com.example.mtgapp.models.Deck
import java.io.File
import com.google.gson.Gson
import kotlinx.coroutines.flow.*
import java.util.UUID
import kotlin.random.Random

class DeckRepository(context: Context) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("decks_prefs", Context.MODE_PRIVATE)
    private val decksDir = context.filesDir.absolutePath + "/decks"

    init {
        File(decksDir).apply {
            if (!exists()) mkdir()
        }
    }

    val decksFlow = MutableStateFlow(getAllDecks())

    private fun updateFlow() { decksFlow.update { getAllDecks() } }

    // Получение всех колод
    fun getAllDecks(): List<Deck> {
        val deckIds = sharedPrefs.getStringSet("deck_ids", setOf()) ?: setOf()
        return deckIds.mapNotNull { getDeckById(it.toInt()) }
    }

    // Получение колоды по ID
    fun getDeckById(id: Int): Deck? {
        val json = sharedPrefs.getString("deck_$id", null)
        return json?.let { Gson().fromJson(it, Deck::class.java) }
    }

    // Добавление новой колоды
    fun createDeck(name: String, imageUrl: String): Deck {
        val newDeck = Deck(
            id = Random.nextInt(),
            name = name,
            imageUrl = imageUrl,
        )
        saveDeck(newDeck)
        return newDeck
    }

    // Удаление колоды
    fun deleteDeck(id: Int) {
        sharedPrefs.edit()
            .remove("deck_$id")
            .apply()
        updateFlow()
    }

    private fun saveDeck(deck: Deck) {
        val editor = sharedPrefs.edit()
        editor.putString("deck_${deck.id}", Gson().toJson(deck))
        editor.putInt("current_id", deck.id)
        editor.putStringSet("deck_ids", sharedPrefs.getStringSet("deck_ids", setOf())?.plus(deck.id.toString()))
        editor.apply()
        updateFlow()
    }
}
