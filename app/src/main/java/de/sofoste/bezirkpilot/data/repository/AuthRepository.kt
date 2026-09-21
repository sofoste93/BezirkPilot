package de.sofoste.bezirkpilot.data.repository

import de.sofoste.bezirkpilot.core.datastore.SessionDataStore
import de.sofoste.bezirkpilot.core.network.ApiService
import de.sofoste.bezirkpilot.core.network.userMessage
import de.sofoste.bezirkpilot.data.remote.dto.ChangePasswordRequest
import de.sofoste.bezirkpilot.data.remote.dto.LoginData
import de.sofoste.bezirkpilot.data.remote.dto.LoginRequest

class AuthRepository(private val api: ApiService, private val session: SessionDataStore) {
    suspend fun login(username: String, password: String): Result<LoginData> = runCatching {
        val response = api.login(LoginRequest(username, password))
        val data = response.data
        if (!response.success || data == null) error(response.message ?: "Anmeldung fehlgeschlagen")
        session.saveSession(data.token, data.user.username, data.user.displayName, data.user.role, data.user.mustChangePassword)
        data
    }.recoverCatching { throw IllegalStateException(it.userMessage("Anmeldung fehlgeschlagen"), it) }

    suspend fun changePassword(currentPassword: String, newPassword: String): Result<Unit> = runCatching {
        val response = api.changePassword(ChangePasswordRequest(currentPassword, newPassword))
        if (!response.success) error(response.message ?: "Passwort konnte nicht geändert werden")
        session.clearSession()
    }.recoverCatching { throw IllegalStateException(it.userMessage("Passwort konnte nicht geändert werden"), it) }

    suspend fun logout(): Result<Unit> = runCatching {
        runCatching { api.logout() }
        session.clearSession()
    }
}