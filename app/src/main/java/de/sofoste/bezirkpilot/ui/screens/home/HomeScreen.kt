package de.sofoste.bezirkpilot.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.domain.model.District

@Composable fun HomeScreen(uiState: HomeUiState, onRetry: () -> Unit, onSearch: (String) -> Unit, onDistrictClick: (District) -> Unit, onDuplicates: () -> Unit, onNewRecipient: () -> Unit, onChangePassword: () -> Unit) {
    var query by remember { mutableStateOf("") }
    Scaffold { padding -> Column(Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(20.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) { Column { Text("Hallo ${uiState.displayName.ifBlank { "Suzi" }} 👋", style = MaterialTheme.typography.headlineMedium); Text("Was möchtest du heute sortieren?", color = MaterialTheme.colorScheme.onSurfaceVariant) }; OutlinedButton(onClick = onChangePassword) { Text("Konto") } }
        Spacer(Modifier.height(28.dp)); Surface(color = MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large, tonalElevation = 2.dp) { Column(Modifier.padding(18.dp)) { Text("Empfänger suchen", style = MaterialTheme.typography.titleMedium); Spacer(Modifier.height(12.dp)); OutlinedTextField(query, { query = it }, label = { Text("Name, Straße oder Bezirk") }, singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), keyboardActions = KeyboardActions(onSearch = { onSearch(query) }), modifier = Modifier.fillMaxWidth()); Spacer(Modifier.height(12.dp)); Button(onClick = { onSearch(query) }, modifier = Modifier.fillMaxWidth()) { Text("Suchen") } } }
        Spacer(Modifier.height(28.dp)); Text("Bezirke", style = MaterialTheme.typography.titleLarge); Text("Direkt nach Zustellbezirk filtern", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(10.dp)); when { uiState.isLoading -> CircularProgressIndicator(); uiState.errorMessage != null -> Column { Text(uiState.errorMessage, color = MaterialTheme.colorScheme.error); Button(onClick = onRetry, modifier = Modifier.padding(top = 8.dp)) { Text("Erneut versuchen") } }; else -> LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) { items(uiState.districts, key = { it.id }) { d -> AssistChip(onClick = { onDistrictClick(d) }, label = { Text("BZ ${d.number}${if (!d.isConfirmed) " ?" else ""}") }) } } }
        Spacer(Modifier.height(30.dp)); Button(onClick = onDuplicates, modifier = Modifier.fillMaxWidth().height(54.dp)) { Text("⚠  Doppelte Straßen") }; Spacer(Modifier.height(12.dp)); OutlinedButton(onClick = onNewRecipient, modifier = Modifier.fillMaxWidth().height(54.dp)) { Text("+  Neuer Eintrag") }; Spacer(Modifier.height(24.dp))
    } }
}