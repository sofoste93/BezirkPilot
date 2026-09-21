package de.sofoste.bezirkpilot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BezirkPilotColorScheme = lightColorScheme(
    primary = BezirkBlack,
    onPrimary = BezirkWhite,
    primaryContainer = PrimaryYellow,
    onPrimaryContainer = BezirkBlack,
    secondary = AccentRed,
    onSecondary = BezirkWhite,
    background = LightGray,
    onBackground = BezirkBlack,
    surface = BezirkWhite,
    onSurface = BezirkBlack,
    outline = DarkGray,
    error = AccentRed,
)

@Composable
fun BezirkPilotTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BezirkPilotColorScheme,
        typography = BezirkPilotTypography,
        content = content,
    )
}

