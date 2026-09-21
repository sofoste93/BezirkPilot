package de.sofoste.bezirkpilot.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(val username: String, val password: String)

data class LoginResponse(
    val success: Boolean,
    val message: String? = null,
    val data: LoginData? = null,
)

data class LoginData(
    val token: String,
    @SerializedName("expires_at") val expiresAt: String,
    val user: UserDto,
)

data class UserDto(
    val id: Int,
    val username: String,
    @SerializedName("display_name") val displayName: String,
    val role: String,
    @SerializedName("must_change_password") val mustChangePassword: Boolean,
)

data class ChangePasswordRequest(
    @SerializedName("current_password") val currentPassword: String,
    @SerializedName("new_password") val newPassword: String,
)

data class BasicResponse(
    val success: Boolean,
    val message: String? = null,
)