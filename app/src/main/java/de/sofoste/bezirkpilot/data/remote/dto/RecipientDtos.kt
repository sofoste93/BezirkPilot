package de.sofoste.bezirkpilot.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipientsResponse(
    val success: Boolean,
    val count: Int,
    val data: List<RecipientDto>,
)

data class RecipientResponse(
    val success: Boolean,
    val message: String? = null,
    val data: RecipientDto,
)

data class RecipientDto(
    val id: Int,
    @SerializedName("display_name") val displayName: String,
    val street: String,
    @SerializedName("house_number") val houseNumber: String,
    val locality: String,
    @SerializedName("is_confirmed") val isConfirmed: Int,
    @SerializedName("district_id") val districtId: Int,
    @SerializedName("postal_code") val postalCode: String,
    @SerializedName("district_number") val districtNumber: String,
    val city: String,
    @SerializedName("district_locality") val districtLocality: String,
    @SerializedName("district_code") val districtCode: String? = null,
)

data class CreateRecipientRequest(
    @SerializedName("display_name") val displayName: String,
    val street: String,
    @SerializedName("house_number") val houseNumber: String,
    val locality: String,
    @SerializedName("district_id") val districtId: Int,
    @SerializedName("is_confirmed") val isConfirmed: Boolean,
)

data class UpdateRecipientRequest(
    @SerializedName("display_name") val displayName: String,
    val street: String,
    @SerializedName("house_number") val houseNumber: String,
    val locality: String,
    @SerializedName("district_id") val districtId: Int,
    @SerializedName("is_confirmed") val isConfirmed: Boolean,
)

data class RecipientHistoryResponse(
    val success: Boolean,
    @SerializedName("recipient_id") val recipientId: Int,
    val count: Int,
    val data: List<RecipientHistoryDto>,
)

data class RecipientHistoryDto(
    val id: Int,
    @SerializedName("recipient_id") val recipientId: Int,
    @SerializedName("change_type") val changeType: String,
    val note: String? = null,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("previous_postal_code") val previousPostalCode: String? = null,
    @SerializedName("previous_district_number") val previousDistrictNumber: String? = null,
    @SerializedName("new_postal_code") val newPostalCode: String,
    @SerializedName("new_district_number") val newDistrictNumber: String,
)