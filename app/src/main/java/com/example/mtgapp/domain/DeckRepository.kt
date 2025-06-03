package com.example.mtgapp.domain


import android.content.Context
import android.content.SharedPreferences
import com.example.mtgapp.models.Deck
import java.io.File
import com.google.gson.Gson

class DeckRepository(context: Context) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("decks_prefs", Context.MODE_PRIVATE)
    private val decksDir = context.filesDir.absolutePath + "/decks"

    init {
        File(decksDir).apply {
            if (!exists()) mkdir()
        }
    }

    // Получение всех колод
    fun getAllDecks(): List<Deck> {
        return mutableListOf<Deck>().apply {
            repeat(20) {
                add(
                    Deck(
                        id = it,
                        name = "Колода $it",
                        imageUrl = ""
                    )
                )
            }
        }
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
            id = getAllDecks().size + 1,
            name = name,
            imageUrl = imageUrl,
        )
        saveDeck(newDeck)
        return newDeck
    }

    // Обновление колоды
    fun updateDeck(deck: Deck) {
        saveDeck(deck)
    }

    // Удаление колоды
    fun deleteDeck(id: Int) {
        sharedPrefs.edit()
            .remove("deck_$id")
            .remove("deck_ids")
            .apply()
    }

    private fun saveDeck(deck: Deck) {
        val editor = sharedPrefs.edit()
        editor.putString("deck_${deck.id}", Gson().toJson(deck))
        editor.putInt("current_id", deck.id)
        editor.putStringSet("deck_ids", sharedPrefs.getStringSet("deck_ids", setOf())?.plus(deck.id.toString()))
        editor.apply()
    }
}
