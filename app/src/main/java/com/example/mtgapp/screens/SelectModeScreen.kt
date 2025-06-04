package com.example.mtgapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.mtgapp.R
import com.example.mtgapp.composable.CommonButton
import com.example.mtgapp.composable.TitleText
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun SelectModeScreen(
    navigateToStandard: () -> Unit,
    navigateToCommander: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(R.drawable.img_select_mode),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
        ) {
            TitleText(text = "Выберите режим",)

            CommonButton(
                onClick = navigateToStandard,
                text = "Standard"
            )

            CommonButton(
                onClick = navigateToCommander,
                text = "Commander"
            )
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