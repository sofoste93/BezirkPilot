package de.sofoste.bezirkpilot.ui.screens.duplicates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.domain.model.DuplicateStreet
import de.sofoste.bezirkpilot.ui.components.AppTopBar
import de.sofoste.bezirkpilot.ui.components.ErrorState
import de.sofoste.bezirkpilot.ui.components.LoadingState

@Composable fun DuplicatesScreen(uiState: DuplicateStreetUiState, onBack: () -> Unit, onRecipientClick: (Int) -> Unit, onRetry: () -> Unit) {
    Scaffold(topBar = { AppTopBar("Doppelte Straßen", onBack) }) { padding -> Box(Modifier.fillMaxSize().padding(padding)) { when { uiState.isLoading -> LoadingState(); uiState.errorMessage != null -> ErrorState(uiState.errorMessage, onRetry); uiState.items.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Keine doppelten Straßen gefunden.") }; else -> LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) { items(uiState.items, key = { "${it.street}-${it.houseNumber}" }) { DuplicateCard(it, onRecipientClick) }; item { Spacer(Modifier.height(16.dp)) } } } } }
}
@Composable private fun DuplicateCard(item: DuplicateStreet, onRecipientClick: (Int) -> Unit) { Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text("${item.street} ${item.houseNumber}", style = MaterialTheme.typography.titleLarge); Text("${item.districtCount} Bezirke · ${item.localityCount} Ortsteile · ${item.recipientCount} Empfänger", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 6.dp)); Spacer(Modifier.height(12.dp)); item.entries.forEach { entry -> Surface(onClick = { onRecipientClick(entry.recipientId) }, color = MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Column(Modifier.padding(14.dp)) { Text(entry.displayName, style = MaterialTheme.typography.titleMedium); Text(entry.locality, color = MaterialTheme.colorScheme.onSurfaceVariant); Text(entry.districtCode, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 4.dp)) } } } } }
}
