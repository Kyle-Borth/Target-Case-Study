package com.target.targetcasestudy.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// TODO Dark colors are same as light for now, discuss with UX team
private val DarkColorScheme = darkColorScheme(
    primary = Red,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = LightGrey,
    secondary = DarkRed,
    onSecondary = White,
    tertiary = Green,
    background = AlmostWhite,
    onBackground = DarkGrey,
    surface = White,
    onSurface = DarkGrey,
    outline = NotSoWhite,
    outlineVariant = NotSoWhite
)

private val LightColorScheme = lightColorScheme(
    primary = Red,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = LightGrey,
    secondary = DarkRed,
    onSecondary = White,
    tertiary = Green,
    background = AlmostWhite,
    onBackground = DarkGrey,
    surface = White,
    onSurface = DarkGrey,
    outline = NotSoWhite,
    outlineVariant = NotSoWhite
)

@Composable
fun TargetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = Typography,
        content = content
    )
}