package de.sofoste.bezirkpilot.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.sofoste.bezirkpilot.ui.theme.BezirkBlack
import de.sofoste.bezirkpilot.ui.theme.BezirkYellow
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1_800)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BezirkYellow),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .background(BezirkBlack)
                    .border(4.dp, Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "BP",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Black,
                    fontSize = 40.sp,
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = "BezirkPilot",
                color = BezirkBlack,
                style = MaterialTheme.typography.displaySmall,
            )
            Text(
                text = "Schnell. Klar. Richtig sortiert.",
                modifier = Modifier.padding(top = 8.dp),
                color = BezirkBlack,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "SYSTEM 1990 // BEREIT",
                modifier = Modifier.padding(top = 28.dp),
                color = BezirkBlack,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}