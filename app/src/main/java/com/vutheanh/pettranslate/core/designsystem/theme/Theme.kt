package com.vutheanh.pettranslate.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Moss,
    onPrimary = Cloud,
    secondary = Clay,
    onSecondary = Cloud,
    background = Cloud,
    onBackground = Ink,
    surface = Sand,
    onSurface = Ink
)

private val DarkColorScheme = darkColorScheme(
    primary = Stone,
    onPrimary = Night,
    secondary = Clay,
    onSecondary = Night,
    background = Night,
    onBackground = Sand,
    surface = Ink,
    onSurface = Sand
)

@Composable
fun PetTranslateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}

