package de.sofoste.bezirkpilot.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.ui.theme.AccentRed
import de.sofoste.bezirkpilot.ui.theme.BezirkBlack
import de.sofoste.bezirkpilot.ui.theme.LightGray
import de.sofoste.bezirkpilot.ui.theme.PrimaryYellow

private val districtCodes = listOf("13", "14", "15", "16", "24", "25", "26", "27", "60")

@Composable
fun HomeScreen(
    onSearch: () -> Unit,
    onDistricts: () -> Unit,
    onDuplicates: () -> Unit,
    onNewRecipient: () -> Unit,
    onSettings: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryYellow)
                .padding(start = 22.dp, end = 22.dp, top = 52.dp, bottom = 22.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = "Hallo Suzi 👋",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Text(
                        text = "Wohin geht die nächste Runde?",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(BezirkBlack, RoundedCornerShape(12.dp))
                        .clickable(onClick = onSettings),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("⚙", color = Color.White)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onSearch),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    text = "⌕  Empfänger, Straße oder Bezirk suchen…",
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp),
                    color = MaterialTheme.colorScheme.outline,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Bezirke", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Alle anzeigen",
                    modifier = Modifier.clickable(onClick = onDistricts),
                    color = AccentRed,
                    fontWeight = FontWeight.Bold,
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 12.dp),
            ) {
                items(districtCodes) { code ->
                    Card(
                        onClick = onDistricts,
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(14.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 18.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "BZ $code",
                                fontWeight = FontWeight.Black,
                            )
                        }
                    }
                }
            }

            OutlinedButton(
                onClick = onDuplicates,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentRed),
            ) {
                Text("⚠  Doppelte Straßen")
            }
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = onNewRecipient,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BezirkBlack,
                    contentColor = Color.White,
                ),
            ) {
                Text("+  Neuer Eintrag")
            }
        }
    }
}

