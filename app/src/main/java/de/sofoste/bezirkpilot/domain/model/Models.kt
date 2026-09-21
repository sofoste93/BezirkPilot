package de.sofoste.bezirkpilot.domain.model

data class District(val id: Int, val postalCode: String, val number: String, val city: String, val locality: String, val description: String?, val isConfirmed: Boolean)
data class Recipient(val id: Int, val displayName: String, val street: String, val houseNumber: String, val locality: String, val districtId: Int, val postalCode: String, val districtNumber: String, val districtCode: String, val isConfirmed: Boolean)
data class RecipientHistory(val id: Int, val previousDistrict: String?, val newDistrict: String, val changeType: String, val note: String?, val createdAt: String)
data class DuplicateStreet(val street: String, val houseNumber: String, val localityCount: Int, val districtCount: Int, val recipientCount: Int, val entries: List<DuplicateStreetEntry>)
data class DuplicateStreetEntry(val recipientId: Int, val displayName: String, val locality: String, val districtCode: String)