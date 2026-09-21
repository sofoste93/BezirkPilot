package de.sofoste.bezirkpilot.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.ui.components.PlaceholderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onChangePassword: () -> Unit,
    onAdminUsers: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Einstellungen") },
                navigationIcon = {
                    Button(
                        onClick = onBack,
                        modifier = Modifier.padding(start = 8.dp),
                    ) {
                        Text("Zurück")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedButton(
                onClick = onChangePassword,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Passwort ändern")
            }
            OutlinedButton(
                onClick = onAdminUsers,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Benutzer verwalten")
            }
        }
    }
}

@Composable
fun ChangePasswordScreen(onBack: () -> Unit) = PlaceholderScreen("Passwort ändern", onBack)

@Composable
fun AdminUsersScreen(onBack: () -> Unit) = PlaceholderScreen("Benutzer verwalten", onBack)
