package de.sofoste.bezirkpilot.domain.model

data class User(
    val id: Int,
    val username: String,
    val displayName: String,
    val role: String,
    val mustChangePassword: Boolean,
)

data class District(
    val id: Int,
    val code: String,
    val name: String,
)

data class Recipient(
    val id: Int,
    val displayName: String,
    val street: String,
    val district: District,
)

data class RecipientHistory(
    val id: Int,
    val recipientId: Int,
    val summary: String,
    val createdAt: String,
)

data class DuplicateStreet(
    val street: String,
    val districts: List<District>,
)

