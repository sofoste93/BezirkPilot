package de.sofoste.bezirkpilot.di

import android.content.Context
import de.sofoste.bezirkpilot.core.datastore.SessionDataStore
import de.sofoste.bezirkpilot.core.network.ApiClient
import de.sofoste.bezirkpilot.data.repository.AuthRepository
import de.sofoste.bezirkpilot.data.repository.DistrictRepository
import de.sofoste.bezirkpilot.data.repository.RecipientRepository
import de.sofoste.bezirkpilot.data.repository.StreetRepository

class AppContainer(context: Context) {
    val sessionDataStore = SessionDataStore(context.applicationContext)
    private val api = ApiClient.create(sessionDataStore)
    val authRepository = AuthRepository(api, sessionDataStore)
    val districtRepository = DistrictRepository(api)
    val recipientRepository = RecipientRepository(api)
    val streetRepository = StreetRepository(api)
}