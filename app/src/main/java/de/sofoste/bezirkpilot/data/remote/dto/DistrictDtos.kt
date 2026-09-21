package de.sofoste.bezirkpilot.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DistrictsResponse(
    val success: Boolean,
    val count: Int,
    val data: List<DistrictDto>,
)

data class DistrictDto(
    val id: Int,
    @SerializedName("postal_code") val postalCode: String,
    @SerializedName("district_number") val districtNumber: String,
    val city: String,
    val locality: String,
    val description: String? = null,
    @SerializedName("is_confirmed") val isConfirmed: Int,
    @SerializedName("is_active") val isActive: Int,
)