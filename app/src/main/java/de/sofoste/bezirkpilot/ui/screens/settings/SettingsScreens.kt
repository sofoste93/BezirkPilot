package de.sofoste.bezirkpilot.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.ui.components.AppTopBar

@Composable fun ChangePasswordScreen(uiState: ChangePasswordUiState, mandatory: Boolean, onCurrentPasswordChange: (String) -> Unit, onNewPasswordChange: (String) -> Unit, onConfirmPasswordChange: (String) -> Unit, onSubmit: () -> Unit, onBack: (() -> Unit)?) {
    Scaffold(topBar = { AppTopBar("Passwort ändern", onBack) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(24.dp)) {
            Text(if (mandatory) "Bitte ändere dein temporäres Passwort, bevor du fortfährst." else "Vergib ein neues, sicheres Passwort.", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(24.dp)); PasswordInput(uiState.currentPassword, onCurrentPasswordChange, "Aktuelles Passwort")
            Spacer(Modifier.height(14.dp)); PasswordInput(uiState.newPassword, onNewPasswordChange, "Neues Passwort")
            Spacer(Modifier.height(14.dp)); PasswordInput(uiState.confirmPassword, onConfirmPasswordChange, "Neues Passwort bestätigen", ImeAction.Done, onSubmit)
            Text("Mindestens 10 Zeichen", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp))
            uiState.errorMessage?.let { Spacer(Modifier.height(12.dp)); Text(it, color = MaterialTheme.colorScheme.error) }
            Spacer(Modifier.height(24.dp)); Button(onClick = onSubmit, enabled = !uiState.isLoading, modifier = Modifier.fillMaxWidth().height(56.dp)) { if (uiState.isLoading) CircularProgressIndicator(Modifier.height(22.dp), strokeWidth = 2.dp) else Text("Passwort ändern") }
        }
    }
}
@Composable private fun PasswordInput(value: String, onChange: (String) -> Unit, label: String, action: ImeAction = ImeAction.Next, onDone: () -> Unit = {}) = OutlinedTextField(value, onChange, label = { Text(label) }, visualTransformation = PasswordVisualTransformation(), singleLine = true, keyboardOptions = KeyboardOptions(imeAction = action), keyboardActions = KeyboardActions(onDone = { onDone() }), modifier = Modifier.fillMaxWidth())