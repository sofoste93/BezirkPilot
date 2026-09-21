package de.sofoste.bezirkpilot.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sofoste.bezirkpilot.data.repository.RecipientRepository
import de.sofoste.bezirkpilot.domain.model.Recipient
import kotlinx.coroutines.launch

data class SearchUiState(val query: String = "", val isLoading: Boolean = false, val results: List<Recipient> = emptyList(), val errorMessage: String? = null, val hasSearched: Boolean = false)
class SearchViewModel(private val repository: RecipientRepository) : ViewModel() {
    var uiState by mutableStateOf(SearchUiState()); private set
    private var initialized = false
    fun initialize(query: String) { if (initialized) return; initialized = true; uiState = uiState.copy(query = query); if (query.isNotBlank()) search() }
    fun onQueryChange(value: String) { uiState = uiState.copy(query = value, errorMessage = null) }
    fun search() {
        val q = uiState.query.trim(); if (q.isBlank()) { uiState = uiState.copy(errorMessage = "Bitte Suchbegriff eingeben"); return }
        viewModelScope.launch { uiState = uiState.copy(isLoading = true, errorMessage = null); val r = repository.search(q); uiState = if (r.isSuccess) uiState.copy(isLoading = false, results = r.getOrDefault(emptyList()), hasSearched = true) else uiState.copy(isLoading = false, results = emptyList(), hasSearched = true, errorMessage = r.exceptionOrNull()?.message ?: "Suche fehlgeschlagen") }
    }
}