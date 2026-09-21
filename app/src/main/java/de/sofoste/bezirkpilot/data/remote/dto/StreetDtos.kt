package de.sofoste.bezirkpilot.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DuplicateStreetsResponse(
    val success: Boolean,
    val count: Int,
    val data: List<DuplicateStreetDto>,
)

data class DuplicateStreetDto(
    val street: String,
    @SerializedName("house_number") val houseNumber: String,
    @SerializedName("locality_count") val localityCount: Int,
    @SerializedName("district_count") val districtCount: Int,
    @SerializedName("recipient_count") val recipientCount: Int,
    val entries: List<DuplicateStreetEntryDto>,
)

data class DuplicateStreetEntryDto(
    val id: Int,
    @SerializedName("display_name") val displayName: String,
    val locality: String,
    @SerializedName("district_code") val districtCode: String,
)