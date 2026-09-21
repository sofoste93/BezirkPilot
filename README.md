# BezirkPilot Android

Private Android cockpit for the BezirkPilot API, built with Kotlin and Jetpack Compose.

## Requirements

- Android Studio with JDK 17
- Android SDK 34
- Minimum supported Android version: API 26

## Current orbit

BezirkPilot 0.1.0 provides the static cockpit used to validate the visual direction
before connecting authentication and business data:

- high-contrast Material 3 theme with the yellow, black, white, red and gray palette;
- direct Login to Home navigation for rapid phone testing;
- readable login form with a restrained 1990s control-room style;
- editable search field, delivery district shortcuts and primary recipient actions;
- prepared Splash and secondary destinations for later authenticated flows.

No API request, token, password or local database is active in this version.

## Build

```powershell
.\gradlew.bat :app:assembleDebug :app:lintDebug
```

Public versions will be published as tagged GitHub Releases with their APK artifact.
