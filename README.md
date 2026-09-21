# BezirkPilot Android

Android cockpit for the BezirkPilot API, built with Kotlin and Jetpack Compose.

## Requirements

- Android Studio with JDK 17
- Android SDK 37
- Minimum supported Android version: API 26

## Current scope

The first milestone contains the application architecture, Material 3 theme,
navigation, and local UI previews for splash, login, and home. Backend access,
session persistence, and Room caching are intentionally deferred to the next
milestones.

## Build

```powershell
.\gradlew.bat :app:assembleDebug
```

