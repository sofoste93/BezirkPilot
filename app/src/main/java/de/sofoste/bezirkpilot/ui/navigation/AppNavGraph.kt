package de.sofoste.bezirkpilot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.sofoste.bezirkpilot.core.util.viewModelFactory
import de.sofoste.bezirkpilot.di.AppContainer
import de.sofoste.bezirkpilot.ui.screens.duplicates.DuplicateStreetViewModel
import de.sofoste.bezirkpilot.ui.screens.duplicates.DuplicatesScreen
import de.sofoste.bezirkpilot.ui.screens.home.HomeScreen
import de.sofoste.bezirkpilot.ui.screens.home.HomeViewModel
import de.sofoste.bezirkpilot.ui.screens.login.LoginScreen
import de.sofoste.bezirkpilot.ui.screens.login.LoginViewModel
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientCreateScreen
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientCreateViewModel
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientDetailScreen
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientDetailViewModel
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientEditScreen
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientEditViewModel
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientHistoryScreen
import de.sofoste.bezirkpilot.ui.screens.recipient.RecipientHistoryViewModel
import de.sofoste.bezirkpilot.ui.screens.search.SearchScreen
import de.sofoste.bezirkpilot.ui.screens.search.SearchViewModel
import de.sofoste.bezirkpilot.ui.screens.settings.ChangePasswordScreen
import de.sofoste.bezirkpilot.ui.screens.settings.ChangePasswordViewModel

@Composable fun AppNavGraph(container: AppContainer) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Route.Login.path) {
        composable(Route.Login.path) { val vm: LoginViewModel = viewModel(factory = viewModelFactory { LoginViewModel(container.authRepository) }); val s = vm.uiState; LaunchedEffect(s.loginSuccess) { if (s.loginSuccess) nav.navigate(if (s.mustChangePassword) Route.ChangePassword.create(true) else Route.Home.path) { popUpTo(Route.Login.path) { inclusive = true } } }; LoginScreen(s, vm::onUsernameChange, vm::onPasswordChange, vm::login) }
        composable(Route.Home.path) { val vm: HomeViewModel = viewModel(factory = viewModelFactory { HomeViewModel(container.districtRepository, container.sessionDataStore) }); HomeScreen(vm.uiState, vm::loadDistricts, { nav.navigate(Route.Search.create(it)) }, { nav.navigate(Route.Search.create(it.number)) }, { nav.navigate(Route.Duplicates.path) }, { nav.navigate(Route.RecipientCreate.path) }, { nav.navigate(Route.ChangePassword.create(false)) }) }
        composable(Route.ChangePassword.path, arguments = listOf(navArgument("mandatory") { type = NavType.BoolType; defaultValue = false })) { entry -> val mandatory = entry.arguments?.getBoolean("mandatory") ?: false; val vm: ChangePasswordViewModel = viewModel(factory = viewModelFactory { ChangePasswordViewModel(container.authRepository) }); LaunchedEffect(vm.uiState.success) { if (vm.uiState.success) nav.navigate(Route.Login.path) { popUpTo(0) } }; ChangePasswordScreen(vm.uiState, mandatory, vm::onCurrentPasswordChange, vm::onNewPasswordChange, vm::onConfirmPasswordChange, vm::submit, if (mandatory) null else ({ nav.popBackStack(); Unit })) }
        composable(Route.Search.path, arguments = listOf(navArgument("query") { type = NavType.StringType; defaultValue = "" })) { entry -> val vm: SearchViewModel = viewModel(factory = viewModelFactory { SearchViewModel(container.recipientRepository) }); LaunchedEffect(Unit) { vm.initialize(entry.arguments?.getString("query").orEmpty()) }; SearchScreen(vm.uiState, vm::onQueryChange, vm::search, { nav.navigate(Route.RecipientDetail.create(it.id)) }, nav::popBackStack) }
        composable(Route.RecipientDetail.path, arguments = listOf(navArgument("recipientId") { type = NavType.IntType })) { entry -> val id = entry.arguments?.getInt("recipientId") ?: return@composable; val vm: RecipientDetailViewModel = viewModel(key = "detail-$id", factory = viewModelFactory { RecipientDetailViewModel(container.recipientRepository) }); val refresh by entry.savedStateHandle.getStateFlow("refresh", true).collectAsState(); LaunchedEffect(id, refresh) { if (refresh) { vm.load(id); entry.savedStateHandle["refresh"] = false } }; RecipientDetailScreen(vm.uiState, nav::popBackStack, { nav.navigate(Route.RecipientEdit.create(it)) }, { nav.navigate(Route.RecipientHistory.create(it)) }, { vm.load(id) }) }
        composable(Route.RecipientEdit.path, arguments = listOf(navArgument("recipientId") { type = NavType.IntType })) { entry -> val id = entry.arguments?.getInt("recipientId") ?: return@composable; val vm: RecipientEditViewModel = viewModel(key = "edit-$id", factory = viewModelFactory { RecipientEditViewModel(container.recipientRepository, container.districtRepository) }); LaunchedEffect(id) { vm.load(id) }; LaunchedEffect(vm.uiState.saveSuccess) { if (vm.uiState.saveSuccess) { nav.previousBackStackEntry?.savedStateHandle?.set("refresh", true); nav.popBackStack() } }; RecipientEditScreen(vm.uiState, vm, nav::popBackStack) }
        composable(Route.RecipientHistory.path, arguments = listOf(navArgument("recipientId") { type = NavType.IntType })) { entry -> val id = entry.arguments?.getInt("recipientId") ?: return@composable; val vm: RecipientHistoryViewModel = viewModel(key = "history-$id", factory = viewModelFactory { RecipientHistoryViewModel(container.recipientRepository) }); LaunchedEffect(id) { vm.load(id) }; RecipientHistoryScreen(vm.uiState, nav::popBackStack) { vm.load(id) } }
        composable(Route.RecipientCreate.path) { val vm: RecipientCreateViewModel = viewModel(factory = viewModelFactory { RecipientCreateViewModel(container.recipientRepository, container.districtRepository) }); val created = vm.uiState.createdRecipientId; LaunchedEffect(created) { if (created != null) nav.navigate(Route.RecipientDetail.create(created)) { popUpTo(Route.RecipientCreate.path) { inclusive = true } } }; RecipientCreateScreen(vm.uiState, vm, nav::popBackStack) }
        composable(Route.Duplicates.path) { val vm: DuplicateStreetViewModel = viewModel(factory = viewModelFactory { DuplicateStreetViewModel(container.streetRepository) }); LaunchedEffect(Unit) { vm.load() }; DuplicatesScreen(vm.uiState, nav::popBackStack, { nav.navigate(Route.RecipientDetail.create(it)) }, vm::load) }
    }
}