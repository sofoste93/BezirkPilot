package de.sofoste.bezirkpilot.ui.navigation

import android.net.Uri

sealed class Route(val path: String) {
    data object Login : Route("login")
    data object Home : Route("home")
    data object ChangePassword : Route("change-password?mandatory={mandatory}") { fun create(mandatory: Boolean) = "change-password?mandatory=$mandatory" }
    data object Search : Route("search?query={query}") { fun create(query: String = "") = "search?query=${Uri.encode(query)}" }
    data object RecipientCreate : Route("recipient/create")
    data object RecipientDetail : Route("recipient/{recipientId}") { fun create(id: Int) = "recipient/$id" }
    data object RecipientEdit : Route("recipient/{recipientId}/edit") { fun create(id: Int) = "recipient/$id/edit" }
    data object RecipientHistory : Route("recipient/{recipientId}/history") { fun create(id: Int) = "recipient/$id/history" }
    data object Duplicates : Route("duplicate-streets")
}