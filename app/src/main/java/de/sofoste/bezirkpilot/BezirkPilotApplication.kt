package de.sofoste.bezirkpilot

import android.app.Application
import de.sofoste.bezirkpilot.di.AppContainer

class BezirkPilotApplication : Application() {
    val container by lazy { AppContainer(this) }
}