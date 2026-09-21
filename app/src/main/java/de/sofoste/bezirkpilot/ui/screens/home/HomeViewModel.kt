package de.sofoste.bezirkpilot.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sofoste.bezirkpilot.core.datastore.SessionDataStore
import de.sofoste.bezirkpilot.data.repository.DistrictRepository
import de.sofoste.bezirkpilot.domain.model.District
import kotlinx.coroutines.launch

data class HomeUiState(val displayName: String = "", val isLoading: Boolean = false, val districts: List<District> = emptyList(), val errorMessage: String? = null)
class HomeViewModel(private val districts: DistrictRepository, private val session: SessionDataStore) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState()); private set
    init { loadDistricts() }
    fun loadDistricts() { viewModelScope.launch {
        uiState = uiState.copy(isLoading = true, errorMessage = null, displayName = session.getSession().displayName.orEmpty())
        val result = districts.getDistricts()
        uiState = if (result.isSuccess) uiState.copy(isLoading = false, districts = result.getOrDefault(emptyList())) else uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message ?: "Bezirke konnten nicht geladen werden")
    } }
}