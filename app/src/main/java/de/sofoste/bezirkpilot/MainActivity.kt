package de.sofoste.bezirkpilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import de.sofoste.bezirkpilot.ui.navigation.AppNavGraph
import de.sofoste.bezirkpilot.ui.theme.BezirkPilotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BezirkPilotTheme {
                AppNavGraph()
            }
        }
    }
}

