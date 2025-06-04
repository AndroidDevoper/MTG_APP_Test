package com.example.mtgapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.composable.CommonButton
import com.example.mtgapp.composable.TitleText
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun DecksCreateScreen(
    onSaveClicked: (String, String) -> Unit,
) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var imageUrl by remember { mutableStateOf(TextFieldValue()) }

    Box {
        TitleText(
            modifier = Modifier.align(Alignment.Center),
            text = "Создайте колоду",
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Название") }
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("url") }
            )

            CommonButton(
                onClick = { onSaveClicked(name.text, imageUrl.text) },
                text = "Создать"
            )
        }
    }
}

@Preview
@Composable
private fun DecksCreateScreenPreview() {
    MTGAPPTheme {
        DecksCreateScreen({_, _ ->})
    }
}