# BezirkPilot Android

Private Android cockpit for the BezirkPilot API, built with Kotlin and Jetpack Compose.

## Download

The current public preview is **BezirkPilot 0.1.0** for Android 8.0 or newer.
It contains static UI only and does not connect to the production API.

- [Download the latest signed APK](https://github.com/sofoste93/BezirkPilot/releases/latest/download/bezirkpilot-latest.apk)
- [Browse every release](https://github.com/sofoste93/BezirkPilot/releases)

Only install APKs published in this official repository.

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

The debug application uses `de.sofoste.bezirkpilot.debug`, so it can coexist with
the signed public application during development.

## Release integrity

Public APKs are built from tagged source, optimized with R8 and signed with the same
long-lived BezirkPilot release certificate. Its SHA-256 digest is:

```text
F4:2D:E2:B4:F1:6B:26:B5:9E:96:FD:50:1F:E4:A2:D9:A4:30:64:E9:51:36:4D:10:2D:E1:B0:3C:69:83:75:B8
```

Release signing values are supplied through private Gradle properties and are never
stored in this repository. Version history and artifact checksums are recorded in
[docs/RELEASES.md](docs/RELEASES.md).
