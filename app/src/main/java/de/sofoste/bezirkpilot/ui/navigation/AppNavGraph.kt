package de.sofoste.bezirkpilot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.sofoste.bezirkpilot.ui.screens.districts.DistrictsScreen
import de.sofoste.bezirkpilot.ui.screens.duplicates.DuplicatesScreen
import de.sofoste.bezirkpilot.ui.screens.home.HomeScreen
import de.sofoste.bezirkpilot.ui.screens.login.LoginScreen
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientDetailScreen
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientEditScreen
import de.sofoste.bezirkpilot.ui.screens.search.SearchScreen
import de.sofoste.bezirkpilot.ui.screens.settings.AdminUsersScreen
import de.sofoste.bezirkpilot.ui.screens.settings.ChangePasswordScreen
import de.sofoste.bezirkpilot.ui.screens.settings.SettingsScreen
import de.sofoste.bezirkpilot.ui.screens.splash.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Splash.path,
    ) {
        composable(Route.Splash.path) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Route.Login.path) {
                        popUpTo(Route.Splash.path) { inclusive = true }
                    }
                },
            )
        }
        composable(Route.Login.path) {
            LoginScreen(
                onLogin = {
                    navController.navigate(Route.Home.path) {
                        popUpTo(Route.Login.path) { inclusive = true }
                    }
                },
            )
        }
        composable(Route.Home.path) {
            HomeScreen(
                onSearch = { navController.navigate(Route.Search.path) },
                onDistricts = { navController.navigate(Route.Districts.path) },
                onDuplicates = { navController.navigate(Route.Duplicates.path) },
                onNewRecipient = { navController.navigate(Route.RecipientEdit.path) },
                onSettings = { navController.navigate(Route.Settings.path) },
            )
        }
        composable(Route.Search.path) { SearchScreen(onBack = navController::popBackStack) }
        composable(Route.RecipientDetail.path) {
            RecipientDetailScreen(onBack = navController::popBackStack)
        }
        composable(Route.RecipientEdit.path) {
            RecipientEditScreen(onBack = navController::popBackStack)
        }
        composable(Route.Districts.path) { DistrictsScreen(onBack = navController::popBackStack) }
        composable(Route.Duplicates.path) { DuplicatesScreen(onBack = navController::popBackStack) }
        composable(Route.Settings.path) {
            SettingsScreen(
                onBack = navController::popBackStack,
                onChangePassword = { navController.navigate(Route.ChangePassword.path) },
                onAdminUsers = { navController.navigate(Route.AdminUsers.path) },
            )
        }
        composable(Route.ChangePassword.path) {
            ChangePasswordScreen(onBack = navController::popBackStack)
        }
        composable(Route.AdminUsers.path) {
            AdminUsersScreen(onBack = navController::popBackStack)
        }
    }
}

