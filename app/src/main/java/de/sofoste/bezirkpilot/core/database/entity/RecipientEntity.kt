package de.sofoste.bezirkpilot.core.database.entity

data class RecipientEntity(
    val id: Int,
    val displayName: String,
    val street: String,
    val districtCode: String,
)

