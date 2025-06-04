package com.example.mtgapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.mtgapp.R
import com.example.mtgapp.composable.CommonButton
import com.example.mtgapp.ui.theme.MTGAPPTheme

@Composable
fun MainScreen(
    navigateToGame: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(R.drawable.img_main),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
        ) {
            CommonButton(
                text = "Начать игру",
                onClick = navigateToGame
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MTGAPPTheme {
        MainScreen({})
    }
}