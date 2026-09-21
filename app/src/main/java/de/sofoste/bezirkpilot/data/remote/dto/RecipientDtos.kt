package de.sofoste.bezirkpilot.data.remote.dto

data class RecipientDto(
    val id: Int,
    val displayName: String,
    val street: String,
    val districtCode: String,
)

data class DistrictDto(
    val id: Int,
    val code: String,
    val name: String,
)

data class RecipientHistoryDto(
    val id: Int,
    val recipientId: Int,
    val summary: String,
    val createdAt: String,
)

data class DuplicateStreetDto(
    val street: String,
    val districtCodes: List<String>,
)

