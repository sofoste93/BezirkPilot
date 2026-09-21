package de.sofoste.bezirkpilot.ui.screens.recipient

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.sofoste.bezirkpilot.ui.components.AppTopBar
import de.sofoste.bezirkpilot.ui.components.ErrorState
import de.sofoste.bezirkpilot.ui.components.LoadingState
import de.sofoste.bezirkpilot.ui.components.RecipientForm

@Composable fun RecipientDetailScreen(uiState: RecipientDetailUiState, onBack: () -> Unit, onEdit: (Int) -> Unit, onHistory: (Int) -> Unit, onRetry: () -> Unit) {
    Scaffold(topBar = { AppTopBar("Empfänger", onBack) }) { padding -> Box(Modifier.fillMaxSize().padding(padding)) { when { uiState.isLoading -> LoadingState(); uiState.errorMessage != null -> ErrorState(uiState.errorMessage, onRetry); uiState.recipient != null -> { val r = uiState.recipient; Column(Modifier.fillMaxSize().padding(20.dp)) { Text(r.displayName, style = MaterialTheme.typography.headlineMedium); Spacer(Modifier.height(22.dp)); DetailRow("Straße", "${r.street} ${r.houseNumber}"); DetailRow("Ortsteil", r.locality); DetailRow("Bezirk", r.districtCode); Spacer(Modifier.height(14.dp)); Surface(shape = MaterialTheme.shapes.small, color = if (r.isConfirmed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.errorContainer) { Text(if (r.isConfirmed) "Bestätigt" else "Noch nicht bestätigt", modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)) }; Spacer(Modifier.height(28.dp)); Button(onClick = { onEdit(r.id) }, modifier = Modifier.fillMaxWidth()) { Text("Bearbeiten") }; Spacer(Modifier.height(12.dp)); OutlinedButton(onClick = { onHistory(r.id) }, modifier = Modifier.fillMaxWidth()) { Text("Änderungsverlauf") } } } } } }
}
@Composable private fun DetailRow(label: String, value: String) { Column(Modifier.padding(vertical = 8.dp)) { Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant); Text(value, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 3.dp)) } }

@Composable fun RecipientEditScreen(uiState: RecipientEditUiState, vm: RecipientEditViewModel, onBack: () -> Unit) { if (uiState.isLoading) LoadingState() else RecipientForm("Empfänger bearbeiten", uiState.displayName, uiState.street, uiState.houseNumber, uiState.locality, uiState.selectedDistrictId, uiState.isConfirmed, uiState.districts, uiState.isSaving, uiState.errorMessage, vm::onDisplayNameChange, vm::onStreetChange, vm::onHouseNumberChange, vm::onLocalityChange, vm::onDistrictChange, vm::onConfirmedChange, vm::save, onBack) }
@Composable fun RecipientCreateScreen(uiState: RecipientCreateUiState, vm: RecipientCreateViewModel, onBack: () -> Unit) { if (uiState.isLoading) LoadingState() else RecipientForm("Neuer Eintrag", uiState.displayName, uiState.street, uiState.houseNumber, uiState.locality, uiState.selectedDistrictId, uiState.isConfirmed, uiState.districts, uiState.isSaving, uiState.errorMessage, vm::onDisplayNameChange, vm::onStreetChange, vm::onHouseNumberChange, vm::onLocalityChange, vm::onDistrictChange, vm::onConfirmedChange, vm::save, onBack) }

@Composable fun RecipientHistoryScreen(uiState: RecipientHistoryUiState, onBack: () -> Unit, onRetry: () -> Unit) {
    Scaffold(topBar = { AppTopBar("Änderungsverlauf", onBack) }) { padding -> Box(Modifier.fillMaxSize().padding(padding)) { when { uiState.isLoading -> LoadingState(); uiState.errorMessage != null -> ErrorState(uiState.errorMessage, onRetry); uiState.items.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Noch keine Änderungen vorhanden.") }; else -> LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) { items(uiState.items, key = { it.id }) { item -> Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp)) { Text("${item.previousDistrict ?: "—"} → ${item.newDistrict}", style = MaterialTheme.typography.titleMedium); Text(item.createdAt, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 6.dp)); item.note?.takeIf { it.isNotBlank() }?.let { Text(it, modifier = Modifier.padding(top = 8.dp)) } } } } } } } }
}