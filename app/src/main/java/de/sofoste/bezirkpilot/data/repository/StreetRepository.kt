package de.sofoste.bezirkpilot.data.repository

import de.sofoste.bezirkpilot.core.network.ApiService
import de.sofoste.bezirkpilot.core.network.userMessage
import de.sofoste.bezirkpilot.data.remote.dto.toDomain
import de.sofoste.bezirkpilot.domain.model.DuplicateStreet

class StreetRepository(private val api: ApiService) {
    suspend fun getDuplicates(): Result<List<DuplicateStreet>> = runCatching {
        api.getDuplicateStreets().data.map { it.toDomain() }
    }.recoverCatching { throw IllegalStateException(it.userMessage("Doppelte Straßen konnten nicht geladen werden"), it) }
}