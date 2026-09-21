package de.sofoste.bezirkpilot.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.sessionDataStore by preferencesDataStore(name = "session")

data class Session(
    val token: String? = null,
    val username: String? = null,
    val displayName: String? = null,
    val role: String? = null,
    val mustChangePassword: Boolean = false,
)

class SessionDataStore(private val context: Context) {
    private companion object {
        val TOKEN = stringPreferencesKey("auth_token")
        val USERNAME = stringPreferencesKey("username")
        val DISPLAY_NAME = stringPreferencesKey("display_name")
        val ROLE = stringPreferencesKey("role")
        val MUST_CHANGE_PASSWORD = booleanPreferencesKey("must_change_password")
    }

    val session: Flow<Session> = context.sessionDataStore.data.map { preferences ->
        Session(
            token = preferences[TOKEN],
            username = preferences[USERNAME],
            displayName = preferences[DISPLAY_NAME],
            role = preferences[ROLE],
            mustChangePassword = preferences[MUST_CHANGE_PASSWORD] ?: false,
        )
    }

    suspend fun saveSession(
        token: String,
        username: String,
        displayName: String,
        role: String,
        mustChangePassword: Boolean,
    ) {
        context.sessionDataStore.edit { preferences ->
            preferences[TOKEN] = token
            preferences[USERNAME] = username
            preferences[DISPLAY_NAME] = displayName
            preferences[ROLE] = role
            preferences[MUST_CHANGE_PASSWORD] = mustChangePassword
        }
    }

    suspend fun getToken(): String? = session.first().token
    suspend fun getSession(): Session = session.first()
    suspend fun getMustChangePassword(): Boolean = session.first().mustChangePassword

    suspend fun clearSession() {
        context.sessionDataStore.edit { it.clear() }
    }
}