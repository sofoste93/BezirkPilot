package de.sofoste.bezirkpilot.ui.screens.duplicates

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sofoste.bezirkpilot.data.repository.StreetRepository
import de.sofoste.bezirkpilot.domain.model.DuplicateStreet
import kotlinx.coroutines.launch

data class DuplicateStreetUiState(val isLoading: Boolean = false, val items: List<DuplicateStreet> = emptyList(), val errorMessage: String? = null)
class DuplicateStreetViewModel(private val repository: StreetRepository) : ViewModel() { var uiState by mutableStateOf(DuplicateStreetUiState()); private set; fun load() { viewModelScope.launch { uiState = DuplicateStreetUiState(isLoading = true); val r = repository.getDuplicates(); uiState = if (r.isSuccess) DuplicateStreetUiState(items = r.getOrDefault(emptyList())) else DuplicateStreetUiState(errorMessage = r.exceptionOrNull()?.message ?: "Doppelte Straßen konnten nicht geladen werden") } } }