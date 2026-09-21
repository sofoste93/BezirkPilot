package de.sofoste.bezirkpilot.ui.navigation

sealed class Route(val path: String) {
    data object Splash : Route("splash")
    data object Login : Route("login")
    data object Home : Route("home")
    data object Search : Route("search")
    data object RecipientDetail : Route("recipient/detail")
    data object RecipientEdit : Route("recipient/edit")
    data object Districts : Route("districts")
    data object Duplicates : Route("duplicates")
    data object Settings : Route("settings")
    data object ChangePassword : Route("settings/change-password")
    data object AdminUsers : Route("settings/admin-users")
}

