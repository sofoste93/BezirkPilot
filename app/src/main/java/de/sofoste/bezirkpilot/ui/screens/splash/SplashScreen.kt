package de.sofoste.bezirkpilot.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.ui.theme.BezirkBlack
import de.sofoste.bezirkpilot.ui.theme.PrimaryYellow
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(900)
        onFinished()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryYellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(112.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(BezirkBlack),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "BP",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall,
            )
        }
        Text(
            text = "BezirkPilot",
            color = BezirkBlack,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

