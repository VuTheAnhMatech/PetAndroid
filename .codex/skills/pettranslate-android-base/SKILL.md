---
name: pettranslate-android-base
description: Use when working on the PetTranslate Android app. Applies this repo's Kotlin, Jetpack Compose, and pragmatic Clean Architecture conventions for feature work, refactors, package placement, state handling, and design-to-UI implementation.
---

# PetTranslate Android Base

Use this skill for all substantial work in this repository.

## Project direction

- Build with `Kotlin + Jetpack Compose`.
- Favor shipping speed with clear boundaries over framework-heavy architecture.
- Keep the app `single-module` for now. Do not introduce multi-module splits unless build time or ownership pain is real.
- Keep DI manual through `AppContainer` until repeated construction pain justifies Hilt/Koin.

## Package layout

Base package: `com.vutheanh.pettranslate`

Place code in these buckets:

- `app/` for app shell, root composables, and app-level wiring
- `core/` for shared theme, DI helpers, and cross-feature utilities
- `feature/<name>/presentation` for `Route`, `Screen`, `UiState`, and `ViewModel`
- `feature/<name>/domain` for pure Kotlin models, repository contracts, and use cases
- `feature/<name>/data` for repository implementations, DTO mapping, and data sources

## Architecture rules

- `domain` must stay Android-free when practical.
- Repository interfaces live in `domain`; implementations live in `data`.
- `presentation` reads use cases, not repository implementations directly.
- Expose immutable UI state objects from `ViewModel`.
- Prefer one `Route` composable that owns the `ViewModel` and one `Screen` composable that renders state.
- Keep feature-specific components inside the feature until at least a second reuse appears.

## Compose rules

- Compose-first. Do not add XML unless integrating a platform view that genuinely needs it.
- Keep composables small and readable; extract only when it reduces duplication or clarifies intent.
- Prefer explicit parameters over hidden singletons inside composables.
- Use previews for leaf screens/components when it helps iteration speed.

## Delivery rules

- When starting a new feature, scaffold `data/domain/presentation` together even if `data` begins with fake data.
- When implementing from screenshots, call out inferred spacing, typography, and states that still need confirmation.
- Run `./gradlew assembleDebug` after structural changes when possible.

