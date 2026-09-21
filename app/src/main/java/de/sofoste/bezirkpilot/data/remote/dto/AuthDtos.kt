package de.sofoste.bezirkpilot.data.remote.dto

data class LoginRequest(
    val username: String,
    val password: String,
)

data class LoginResponse(
    val token: String,
    val expiresAt: String,
    val user: UserDto,
)

data class UserDto(
    val id: Int,
    val username: String,
    val displayName: String,
    val role: String,
    val mustChangePassword: Boolean,
)

