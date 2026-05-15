package com.shilpakala.showcase.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = TempleGold,
    onPrimary = CharcoalPrimary,
    secondary = Terracotta,
    background = GalleryBackground,
    surface = GalleryCardBg,
    onBackground = CreamWhite,
    onSurface = CreamWhite
)

@Composable
fun ShilpaKalaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
