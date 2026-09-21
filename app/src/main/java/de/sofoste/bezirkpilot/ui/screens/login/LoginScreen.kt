package de.sofoste.bezirkpilot.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp

@Composable fun LoginScreen(uiState: LoginUiState, onUsernameChange: (String) -> Unit, onPasswordChange: (String) -> Unit, onLoginClick: () -> Unit) {
    Column(Modifier.fillMaxSize().statusBarsPadding().padding(24.dp), verticalArrangement = Arrangement.Center) {
        Box(contentAlignment = Alignment.Center) { Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primary, modifier = Modifier.height(56.dp).fillMaxWidth(.18f)) { Box(contentAlignment = Alignment.Center) { Text("BP", fontWeight = FontWeight.Bold) } } }
        Spacer(Modifier.height(24.dp)); Text("Willkommen bei BezirkPilot", style = MaterialTheme.typography.headlineMedium)
        Text("Schnell finden, sicher zuordnen.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp))
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(uiState.username, onUsernameChange, label = { Text("Benutzername") }, singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(uiState.password, onPasswordChange, label = { Text("Passwort") }, visualTransformation = PasswordVisualTransformation(), singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = { onLoginClick() }), modifier = Modifier.fillMaxWidth())
        uiState.errorMessage?.let { Spacer(Modifier.height(12.dp)); Text(it, color = MaterialTheme.colorScheme.error) }
        Spacer(Modifier.height(24.dp)); Button(onClick = onLoginClick, enabled = !uiState.isLoading, modifier = Modifier.fillMaxWidth().height(56.dp)) { if (uiState.isLoading) CircularProgressIndicator(Modifier.height(22.dp), strokeWidth = 2.dp) else Text("Anmelden") }
    }
}