# BezirkPilot Android

Private Android cockpit for the BezirkPilot API, built with Kotlin and Jetpack Compose.

## Public preview

The current public release is **BezirkPilot 0.1.0** for Android 8.0 or newer. It is
the static UI preview and remains available while the authenticated V1 workflow is
validated against the local development API.

- [Download the latest signed APK](https://github.com/sofoste93/BezirkPilot/releases/latest/download/bezirkpilot-latest.apk)
- [Browse every release](https://github.com/sofoste93/BezirkPilot/releases)

## Current development workflow

The debug application now supports the complete first recipient workflow:

- private login with a persisted Bearer token;
- mandatory password change for temporary passwords;
- live delivery districts from the API;
- recipient search by name, street, locality, postal code or district;
- recipient detail, editing and correction history;
- new recipient creation;
- duplicate street overview with direct links to recipient details;
- loading, empty, validation, network and retry states.

The app uses a small manual dependency container, Retrofit, OkHttp, DataStore and
Compose ViewModels. API DTOs are mapped to domain models before they reach the UI.

## Local API on a physical phone

The debug build currently targets the development computer on the local network:

```text
http://192.168.178.30:7000/
```

Start the PHP API on all LAN interfaces from the backend repository:

```powershell
& 'C:\xampp\php\php.exe' -S 0.0.0.0:7000 -t public public/index.php
```

The Galaxy and the computer must be connected to the same local network. Cleartext
HTTP is enabled only for the debug build. Override the debug URL without editing
source code by setting this Gradle property:

```properties
BEZIRKPILOT_DEBUG_API_BASE_URL=http://192.168.x.x:7000/
```

The release build has a separate production URL and keeps cleartext HTTP disabled.
No production release of the authenticated workflow is published until the local
phone tests are complete.

## Build

Requirements: Android Studio, JDK 17, Android SDK 34 and minimum Android API 26.

```powershell
.\gradlew.bat :app:assembleDebug :app:lintDebug
```

The debug package is `de.sofoste.bezirkpilot.debug`, so it can coexist with the
signed public application.

## Release integrity

Public APKs are built from tagged source, optimized with R8 and signed with the same
long-lived BezirkPilot release certificate. Its SHA-256 digest is:

```text
F4:2D:E2:B4:F1:6B:26:B5:9E:96:FD:50:1F:E4:A2:D9:A4:30:64:E9:51:36:4D:10:2D:E1:B0:3C:69:83:75:B8
```

Release signing values are supplied through private Gradle properties and are never
stored in this repository. Version history and artifact checksums are recorded in
[docs/RELEASES.md](docs/RELEASES.md).