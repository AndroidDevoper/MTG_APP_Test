package com.example.mtgapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun SelectModeScreen(
    navigateToStandard: () -> Unit,
    navigateToCommander: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = navigateToStandard,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Standart")
        }

        Button(
            onClick = navigateToCommander,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Commander")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectModePreview() {
    MTGAPPTheme {
        SelectModeScreen({},{})
    }
}