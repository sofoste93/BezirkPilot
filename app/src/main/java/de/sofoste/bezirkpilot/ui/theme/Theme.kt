package de.sofoste.bezirkpilot.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val BezirkPilotColorScheme = lightColorScheme(
    primary = BezirkYellow,
    onPrimary = BezirkBlack,
    primaryContainer = BezirkYellow,
    onPrimaryContainer = BezirkBlack,
    secondary = BezirkBlack,
    onSecondary = BezirkWhite,
    secondaryContainer = BezirkDarkGray,
    onSecondaryContainer = BezirkWhite,
    error = BezirkRed,
    onError = BezirkWhite,
    background = BezirkLightGray,
    onBackground = BezirkBlack,
    surface = BezirkWhite,
    onSurface = BezirkBlack,
    surfaceVariant = BezirkLightGray,
    onSurfaceVariant = BezirkDarkGray,
    outline = BezirkMediumGray,
)

private val BezirkPilotShapes = Shapes(
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(10.dp),
)

@Composable
fun BezirkPilotTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BezirkPilotColorScheme,
        typography = BezirkPilotTypography,
        shapes = BezirkPilotShapes,
        content = content,
    )
}