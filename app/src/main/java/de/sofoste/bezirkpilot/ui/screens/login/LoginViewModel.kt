package de.sofoste.bezirkpilot.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sofoste.bezirkpilot.data.repository.AuthRepository
import kotlinx.coroutines.launch

data class LoginUiState(val username: String = "", val password: String = "", val isLoading: Boolean = false, val errorMessage: String? = null, val loginSuccess: Boolean = false, val mustChangePassword: Boolean = false)

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState()); private set
    fun onUsernameChange(value: String) { uiState = uiState.copy(username = value, errorMessage = null) }
    fun onPasswordChange(value: String) { uiState = uiState.copy(password = value, errorMessage = null) }
    fun login() {
        val state = uiState
        if (state.username.isBlank() || state.password.isBlank()) { uiState = state.copy(errorMessage = "Benutzername und Passwort erforderlich"); return }
        viewModelScope.launch {
            uiState = state.copy(isLoading = true, errorMessage = null)
            val result = repository.login(state.username.trim(), state.password)
            val data = result.getOrNull()
            uiState = if (data != null) state.copy(isLoading = false, loginSuccess = true, mustChangePassword = data.user.mustChangePassword)
            else state.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message ?: "Anmeldung fehlgeschlagen")
        }
    }
}