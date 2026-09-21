package de.sofoste.bezirkpilot.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sofoste.bezirkpilot.data.repository.AuthRepository
import kotlinx.coroutines.launch

data class ChangePasswordUiState(val currentPassword: String = "", val newPassword: String = "", val confirmPassword: String = "", val isLoading: Boolean = false, val errorMessage: String? = null, val success: Boolean = false)
class ChangePasswordViewModel(private val repository: AuthRepository) : ViewModel() {
    var uiState by mutableStateOf(ChangePasswordUiState()); private set
    fun onCurrentPasswordChange(v: String) { uiState = uiState.copy(currentPassword = v, errorMessage = null) }
    fun onNewPasswordChange(v: String) { uiState = uiState.copy(newPassword = v, errorMessage = null) }
    fun onConfirmPasswordChange(v: String) { uiState = uiState.copy(confirmPassword = v, errorMessage = null) }
    fun submit() {
        val s = uiState
        val error = when {
            s.currentPassword.isBlank() || s.newPassword.isBlank() || s.confirmPassword.isBlank() -> "Alle Felder sind erforderlich"
            s.newPassword.length < 10 -> "Das neue Passwort muss mindestens 10 Zeichen enthalten"
            s.newPassword != s.confirmPassword -> "Die neuen Passwörter stimmen nicht überein"
            s.currentPassword == s.newPassword -> "Das neue Passwort muss sich vom alten unterscheiden"
            else -> null
        }
        if (error != null) { uiState = s.copy(errorMessage = error); return }
        viewModelScope.launch {
            uiState = s.copy(isLoading = true, errorMessage = null)
            val result = repository.changePassword(s.currentPassword, s.newPassword)
            uiState = if (result.isSuccess) s.copy(isLoading = false, success = true) else s.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message ?: "Passwort konnte nicht geändert werden")
        }
    }
}