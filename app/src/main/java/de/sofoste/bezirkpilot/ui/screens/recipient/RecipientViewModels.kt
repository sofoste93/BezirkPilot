package de.sofoste.bezirkpilot.ui.screens.recipient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sofoste.bezirkpilot.data.repository.DistrictRepository
import de.sofoste.bezirkpilot.data.repository.RecipientRepository
import de.sofoste.bezirkpilot.domain.model.District
import de.sofoste.bezirkpilot.domain.model.Recipient
import de.sofoste.bezirkpilot.domain.model.RecipientHistory
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

data class RecipientDetailUiState(val isLoading: Boolean = false, val recipient: Recipient? = null, val errorMessage: String? = null)
class RecipientDetailViewModel(private val repository: RecipientRepository) : ViewModel() {
    var uiState by mutableStateOf(RecipientDetailUiState()); private set
    fun load(id: Int) { viewModelScope.launch { uiState = uiState.copy(isLoading = true, errorMessage = null); val r = repository.getById(id); uiState = if (r.isSuccess) RecipientDetailUiState(recipient = r.getOrNull()) else RecipientDetailUiState(errorMessage = r.exceptionOrNull()?.message ?: "Empfänger konnte nicht geladen werden") } }
}

data class RecipientEditUiState(val isLoading: Boolean = false, val isSaving: Boolean = false, val recipientId: Int? = null, val displayName: String = "", val street: String = "", val houseNumber: String = "", val locality: String = "", val selectedDistrictId: Int? = null, val isConfirmed: Boolean = true, val districts: List<District> = emptyList(), val errorMessage: String? = null, val saveSuccess: Boolean = false)
class RecipientEditViewModel(private val recipients: RecipientRepository, private val districtsRepository: DistrictRepository) : ViewModel() {
    var uiState by mutableStateOf(RecipientEditUiState()); private set
    private var loadedId: Int? = null
    fun load(id: Int) { if (loadedId == id) return; loadedId = id; viewModelScope.launch { uiState = RecipientEditUiState(isLoading = true); val rr = async { recipients.getById(id) }; val dr = async { districtsRepository.getDistricts() }; val recipient = rr.await().getOrNull(); val districts = dr.await().getOrNull(); uiState = if (recipient == null || districts == null) RecipientEditUiState(errorMessage = "Daten konnten nicht geladen werden") else RecipientEditUiState(recipientId = recipient.id, displayName = recipient.displayName, street = recipient.street, houseNumber = recipient.houseNumber, locality = recipient.locality, selectedDistrictId = recipient.districtId, isConfirmed = recipient.isConfirmed, districts = districts) } }
    fun onDisplayNameChange(v: String) { uiState = uiState.copy(displayName = v, errorMessage = null) }; fun onStreetChange(v: String) { uiState = uiState.copy(street = v, errorMessage = null) }; fun onHouseNumberChange(v: String) { uiState = uiState.copy(houseNumber = v, errorMessage = null) }; fun onLocalityChange(v: String) { uiState = uiState.copy(locality = v, errorMessage = null) }; fun onDistrictChange(v: Int) { uiState = uiState.copy(selectedDistrictId = v, errorMessage = null) }; fun onConfirmedChange(v: Boolean) { uiState = uiState.copy(isConfirmed = v) }
    fun save() { val s = uiState; val id = s.recipientId ?: return; val district = s.selectedDistrictId; if (s.displayName.isBlank() || s.street.isBlank() || s.houseNumber.isBlank() || s.locality.isBlank() || district == null) { uiState = s.copy(errorMessage = "Bitte alle Pflichtfelder ausfüllen"); return }; viewModelScope.launch { uiState = s.copy(isSaving = true, errorMessage = null); val r = recipients.update(id, s.displayName.trim(), s.street.trim(), s.houseNumber.trim(), s.locality.trim(), district, s.isConfirmed); uiState = if (r.isSuccess) s.copy(isSaving = false, saveSuccess = true) else s.copy(isSaving = false, errorMessage = r.exceptionOrNull()?.message ?: "Speichern fehlgeschlagen") } }
}

data class RecipientHistoryUiState(val isLoading: Boolean = false, val items: List<RecipientHistory> = emptyList(), val errorMessage: String? = null)
class RecipientHistoryViewModel(private val repository: RecipientRepository) : ViewModel() { var uiState by mutableStateOf(RecipientHistoryUiState()); private set; fun load(id: Int) { viewModelScope.launch { uiState = RecipientHistoryUiState(isLoading = true); val r = repository.getHistory(id); uiState = if (r.isSuccess) RecipientHistoryUiState(items = r.getOrDefault(emptyList())) else RecipientHistoryUiState(errorMessage = r.exceptionOrNull()?.message ?: "Verlauf konnte nicht geladen werden") } } }

data class RecipientCreateUiState(val displayName: String = "", val street: String = "", val houseNumber: String = "", val locality: String = "", val selectedDistrictId: Int? = null, val isConfirmed: Boolean = true, val districts: List<District> = emptyList(), val isLoading: Boolean = false, val isSaving: Boolean = false, val errorMessage: String? = null, val createdRecipientId: Int? = null)
class RecipientCreateViewModel(private val recipients: RecipientRepository, private val districtsRepository: DistrictRepository) : ViewModel() {
    var uiState by mutableStateOf(RecipientCreateUiState()); private set
    init { viewModelScope.launch { uiState = uiState.copy(isLoading = true); val r = districtsRepository.getDistricts(); uiState = if (r.isSuccess) uiState.copy(isLoading = false, districts = r.getOrDefault(emptyList())) else uiState.copy(isLoading = false, errorMessage = r.exceptionOrNull()?.message ?: "Bezirke konnten nicht geladen werden") } }
    fun onDisplayNameChange(v: String) { uiState = uiState.copy(displayName = v, errorMessage = null) }; fun onStreetChange(v: String) { uiState = uiState.copy(street = v, errorMessage = null) }; fun onHouseNumberChange(v: String) { uiState = uiState.copy(houseNumber = v, errorMessage = null) }; fun onLocalityChange(v: String) { uiState = uiState.copy(locality = v, errorMessage = null) }; fun onDistrictChange(v: Int) { uiState = uiState.copy(selectedDistrictId = v, errorMessage = null) }; fun onConfirmedChange(v: Boolean) { uiState = uiState.copy(isConfirmed = v) }
    fun save() { val s = uiState; val district = s.selectedDistrictId; if (s.displayName.isBlank() || s.street.isBlank() || s.houseNumber.isBlank() || s.locality.isBlank() || district == null) { uiState = s.copy(errorMessage = "Bitte alle Pflichtfelder ausfüllen"); return }; viewModelScope.launch { uiState = s.copy(isSaving = true, errorMessage = null); val r = recipients.create(s.displayName.trim(), s.street.trim(), s.houseNumber.trim(), s.locality.trim(), district, s.isConfirmed); uiState = if (r.isSuccess) s.copy(isSaving = false, createdRecipientId = r.getOrThrow().id) else s.copy(isSaving = false, errorMessage = r.exceptionOrNull()?.message ?: "Speichern fehlgeschlagen") } }
}