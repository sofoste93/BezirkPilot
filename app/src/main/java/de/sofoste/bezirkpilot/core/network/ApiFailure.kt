package de.sofoste.bezirkpilot.core.network

import com.google.gson.Gson
import de.sofoste.bezirkpilot.data.remote.dto.BasicResponse
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

fun Throwable.userMessage(fallback: String): String = when (this) {
    is HttpException -> runCatching {
        Gson().fromJson(response()?.errorBody()?.string(), BasicResponse::class.java).message
    }.getOrNull()?.takeIf { !it.isNullOrBlank() } ?: when (code()) {
        401 -> "Anmeldung abgelaufen. Bitte erneut anmelden."
        403 -> "Keine Berechtigung für diese Aktion."
        404 -> "Eintrag nicht gefunden."
        else -> fallback
    }
    is ConnectException, is UnknownHostException -> "Server nicht erreichbar. Bitte Verbindung prüfen."
    is SocketTimeoutException -> "Der Server antwortet zu langsam. Bitte erneut versuchen."
    else -> message?.takeIf { it.isNotBlank() } ?: fallback
}