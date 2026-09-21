package de.sofoste.bezirkpilot.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.sofoste.bezirkpilot.ui.theme.BezirkBlack
import de.sofoste.bezirkpilot.ui.theme.BezirkDarkGray
import de.sofoste.bezirkpilot.ui.theme.BezirkLightGray
import de.sofoste.bezirkpilot.ui.theme.BezirkMediumGray
import de.sofoste.bezirkpilot.ui.theme.BezirkRed
import de.sofoste.bezirkpilot.ui.theme.BezirkWhite
import de.sofoste.bezirkpilot.ui.theme.BezirkYellow

private val districtCodes = listOf("13", "14", "15", "16", "24", "25", "26", "27", "60")

@Composable
fun HomeScreen(
    onSearch: () -> Unit,
    onDistricts: () -> Unit,
    onDuplicates: () -> Unit,
    onNewRecipient: () -> Unit,
    onSettings: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BezirkLightGray)
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BezirkYellow)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 18.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "BEZIRKPILOT // BZ-ZENTRALE",
                        style = MaterialTheme.typography.labelSmall,
                    )
                    Text(
                        text = "Hallo Suzi \uD83D\uDC4B",
                        modifier = Modifier.padding(top = 6.dp),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(BezirkBlack)
                        .border(2.dp, BezirkWhite)
                        .clickable(onClick = onSettings),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "SET",
                        color = BezirkWhite,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        fontSize = 13.sp,
                    )
                }
            }
            Text(
                text = "Wen oder welche Adresse suchst du?",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 18.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BezirkDarkGray)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "SYSTEM BEREIT",
                    color = BezirkYellow,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = "09 BEZIRKE",
                    color = BezirkWhite,
                    style = MaterialTheme.typography.labelSmall,
                )
            }

            Spacer(Modifier.height(18.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Name, Stra\u00DFe oder Bezirk") },
                placeholder = { Text("z. B. Donald oder Hauptstra\u00DFe") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearch() }),
                trailingIcon = {
                    TextButton(onClick = onSearch) {
                        Text(
                            text = "LOS",
                            color = BezirkBlack,
                            fontWeight = FontWeight.Black,
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BezirkBlack,
                    unfocusedBorderColor = BezirkMediumGray,
                    focusedContainerColor = BezirkWhite,
                    unfocusedContainerColor = BezirkWhite,
                    cursorColor = BezirkBlack,
                ),
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Bezirke",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "ALLE \u2192",
                    modifier = Modifier
                        .clickable(onClick = onDistricts)
                        .padding(vertical = 8.dp),
                    color = BezirkRed,
                    style = MaterialTheme.typography.labelSmall,
                )
            }

            LazyRow(
                modifier = Modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(districtCodes) { district ->
                    AssistChip(
                        onClick = onDistricts,
                        label = {
                            Text(
                                text = "BZ $district",
                                fontWeight = FontWeight.Black,
                            )
                        },
                        shape = RoundedCornerShape(2.dp),
                        border = BorderStroke(2.dp, BezirkBlack),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = BezirkWhite,
                            labelColor = BezirkBlack,
                        ),
                    )
                }
            }

            Spacer(Modifier.height(30.dp))

            Text(
                text = "SCHNELLZUGRIFF",
                color = BezirkMediumGray,
                style = MaterialTheme.typography.labelSmall,
            )
            Spacer(Modifier.height(10.dp))

            Button(
                onClick = onDuplicates,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(2.dp, BezirkBlack),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BezirkRed,
                    contentColor = Color.White,
                ),
            ) {
                Text("\u26A0  DOPPELTE STRASSEN")
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onNewRecipient,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(2.dp, BezirkBlack),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = BezirkYellow,
                    contentColor = BezirkBlack,
                ),
            ) {
                Text("+  NEUER EINTRAG")
            }

            Text(
                text = "BEZIRKPILOT 0.1 // BEREIT",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 26.dp, bottom = 10.dp),
                color = BezirkMediumGray,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}