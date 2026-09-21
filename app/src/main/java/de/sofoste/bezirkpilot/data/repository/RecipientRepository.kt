package de.sofoste.bezirkpilot.data.repository

import de.sofoste.bezirkpilot.core.network.ApiService
import de.sofoste.bezirkpilot.core.network.userMessage
import de.sofoste.bezirkpilot.data.remote.dto.CreateRecipientRequest
import de.sofoste.bezirkpilot.data.remote.dto.UpdateRecipientRequest
import de.sofoste.bezirkpilot.data.remote.dto.toDomain
import de.sofoste.bezirkpilot.domain.model.Recipient
import de.sofoste.bezirkpilot.domain.model.RecipientHistory

class RecipientRepository(private val api: ApiService) {
    suspend fun search(query: String): Result<List<Recipient>> = call("Suche fehlgeschlagen") { api.searchRecipients(query.trim()).data.map { it.toDomain() } }
    suspend fun getById(id: Int): Result<Recipient> = call("Empfänger konnte nicht geladen werden") { api.getRecipientById(id).data.toDomain() }
    suspend fun getHistory(id: Int): Result<List<RecipientHistory>> = call("Änderungsverlauf konnte nicht geladen werden") { api.getRecipientHistory(id).data.map { it.toDomain() } }
    suspend fun create(displayName: String, street: String, houseNumber: String, locality: String, districtId: Int, isConfirmed: Boolean): Result<Recipient> = call("Empfänger konnte nicht erstellt werden") {
        api.createRecipient(CreateRecipientRequest(displayName, street, houseNumber, locality, districtId, isConfirmed)).data.toDomain()
    }
    suspend fun update(id: Int, displayName: String, street: String, houseNumber: String, locality: String, districtId: Int, isConfirmed: Boolean): Result<Recipient> = call("Empfänger konnte nicht aktualisiert werden") {
        api.updateRecipient(id, UpdateRecipientRequest(displayName, street, houseNumber, locality, districtId, isConfirmed)).data.toDomain()
    }
    private suspend fun <T> call(fallback: String, block: suspend () -> T): Result<T> = runCatching { block() }.recoverCatching { throw IllegalStateException(it.userMessage(fallback), it) }
}