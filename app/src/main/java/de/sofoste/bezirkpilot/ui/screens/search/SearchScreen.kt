package de.sofoste.bezirkpilot.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.domain.model.Recipient
import de.sofoste.bezirkpilot.ui.components.AppTopBar

@Composable fun SearchScreen(uiState: SearchUiState, onQueryChange: (String) -> Unit, onSearch: () -> Unit, onRecipientClick: (Recipient) -> Unit, onBack: () -> Unit) {
    Scaffold(topBar = { AppTopBar("Empfänger suchen", onBack) }) { padding -> Column(Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp)) {
        OutlinedTextField(uiState.query, onQueryChange, label = { Text("Name, Straße oder Bezirk") }, singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), keyboardActions = KeyboardActions(onSearch = { onSearch() }), modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp)); Button(onClick = onSearch, enabled = !uiState.isLoading, modifier = Modifier.fillMaxWidth()) { if (uiState.isLoading) CircularProgressIndicator(Modifier.height(22.dp), strokeWidth = 2.dp) else Text("Suchen") }
        uiState.errorMessage?.let { Spacer(Modifier.height(12.dp)); Text(it, color = MaterialTheme.colorScheme.error) }
        if (uiState.hasSearched && uiState.results.isEmpty() && uiState.errorMessage == null) { Spacer(Modifier.height(28.dp)); Text("Keine Treffer gefunden.", style = MaterialTheme.typography.bodyLarge) }
        Spacer(Modifier.height(16.dp)); LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) { items(uiState.results, key = { it.id }) { RecipientResultCard(it) { onRecipientClick(it) } }; item { Spacer(Modifier.height(20.dp)) } }
    } }
}
@Composable private fun RecipientResultCard(recipient: Recipient, onClick: () -> Unit) { Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) { Text(recipient.displayName, style = MaterialTheme.typography.titleMedium); Text("${recipient.street} ${recipient.houseNumber}", modifier = Modifier.padding(top = 6.dp)); Text(recipient.locality, color = MaterialTheme.colorScheme.onSurfaceVariant); Spacer(Modifier.height(12.dp)); Surface(color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small) { Text("BZ ${recipient.districtNumber}  ·  ${recipient.districtCode}", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) }; if (!recipient.isConfirmed) Text("Noch nicht bestätigt", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp)) } }
}
