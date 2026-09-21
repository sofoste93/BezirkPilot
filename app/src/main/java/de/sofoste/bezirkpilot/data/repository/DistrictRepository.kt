package de.sofoste.bezirkpilot.data.repository

import de.sofoste.bezirkpilot.core.network.ApiService
import de.sofoste.bezirkpilot.core.network.userMessage
import de.sofoste.bezirkpilot.data.remote.dto.toDomain
import de.sofoste.bezirkpilot.domain.model.District

class DistrictRepository(private val api: ApiService) {
    suspend fun getDistricts(): Result<List<District>> = runCatching {
        val response = api.getDistricts()
        if (!response.success) error("Bezirke konnten nicht geladen werden")
        response.data.map { it.toDomain() }
    }.recoverCatching { throw IllegalStateException(it.userMessage("Bezirke konnten nicht geladen werden"), it) }
}