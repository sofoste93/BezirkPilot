package de.sofoste.bezirkpilot.data.remote.dto

import de.sofoste.bezirkpilot.domain.model.District
import de.sofoste.bezirkpilot.domain.model.DuplicateStreet
import de.sofoste.bezirkpilot.domain.model.DuplicateStreetEntry
import de.sofoste.bezirkpilot.domain.model.Recipient
import de.sofoste.bezirkpilot.domain.model.RecipientHistory

fun DistrictDto.toDomain() = District(id, postalCode, districtNumber, city, locality, description, isConfirmed == 1)
fun RecipientDto.toDomain() = Recipient(id, displayName, street, houseNumber, locality, districtId, postalCode, districtNumber, districtCode ?: "$postalCode-$districtNumber", isConfirmed == 1)
fun RecipientHistoryDto.toDomain() = RecipientHistory(id, previousDistrictNumber?.let { "${previousPostalCode.orEmpty()}-$it" }, "$newPostalCode-$newDistrictNumber", changeType, note, createdAt)
fun DuplicateStreetDto.toDomain() = DuplicateStreet(street, houseNumber, localityCount, districtCount, recipientCount, entries.map { DuplicateStreetEntry(it.id, it.displayName, it.locality, it.districtCode) })