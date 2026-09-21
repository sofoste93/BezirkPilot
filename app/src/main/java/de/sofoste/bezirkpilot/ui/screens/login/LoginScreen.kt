package de.sofoste.bezirkpilot.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.sofoste.bezirkpilot.ui.theme.BezirkBlack
import de.sofoste.bezirkpilot.ui.theme.BezirkDarkGray
import de.sofoste.bezirkpilot.ui.theme.BezirkLightGray
import de.sofoste.bezirkpilot.ui.theme.BezirkMediumGray
import de.sofoste.bezirkpilot.ui.theme.BezirkWhite
import de.sofoste.bezirkpilot.ui.theme.BezirkYellow

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BezirkLightGray)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BezirkYellow)
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 24.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .background(BezirkBlack)
                        .border(2.dp, BezirkWhite),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "BP",
                        color = BezirkWhite,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        fontSize = 22.sp,
                    )
                }
                Column {
                    Text(
                        text = "BEZIRKPILOT",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Text(
                        text = "BZ-ZENTRALE // INTERN",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Willkommen an Bord",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Schnell. Klar. Richtig sortiert.",
                modifier = Modifier.padding(top = 6.dp),
                color = BezirkMediumGray,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(Modifier.height(30.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(start = 6.dp, top = 6.dp)
                        .background(BezirkDarkGray),
                )
                Surface(
                    modifier = Modifier.padding(end = 6.dp, bottom = 6.dp),
                    color = BezirkWhite,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(2.dp, BezirkBlack),
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "ANMELDUNG",
                            style = MaterialTheme.typography.labelSmall,
                            color = BezirkMediumGray,
                        )
                        Spacer(Modifier.height(18.dp))
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text("Benutzername") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            colors = retroTextFieldColors(),
                            shape = RoundedCornerShape(2.dp),
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Passwort") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done,
                            ),
                            keyboardActions = KeyboardActions(onDone = { onLogin() }),
                            colors = retroTextFieldColors(),
                            shape = RoundedCornerShape(2.dp),
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(Modifier.height(22.dp))
                        Button(
                            onClick = onLogin,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(2.dp),
                            border = BorderStroke(2.dp, BezirkBlack),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BezirkYellow,
                                contentColor = BezirkBlack,
                            ),
                        ) {
                            Text("ANMELDEN  \u2192")
                        }
                    }
                }
            }

            Text(
                text = "ZUGANGSBEREICH // BEREIT",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 28.dp),
                color = BezirkMediumGray,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
private fun retroTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = BezirkBlack,
    unfocusedBorderColor = BezirkMediumGray,
    focusedLabelColor = BezirkBlack,
    unfocusedLabelColor = BezirkMediumGray,
    cursorColor = BezirkBlack,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
)