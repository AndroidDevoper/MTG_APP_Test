package com.example.mtgapp.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center,
        fontSize = TextUnit(24f, TextUnitType.Sp),
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}