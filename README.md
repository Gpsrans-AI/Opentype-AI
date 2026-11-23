# OpenType AI Keyboard

An open-source Android keyboard project targeting Android 15 devices, initially tested on an Honor 90.

## Current status

This is **Milestone 0: project skeleton / IME proof of concept**.

Included:
- Android application shell
- Android InputMethodService registration
- Minimal on-screen English keyboard
- Keyboard settings shortcut and input-method picker
- GitHub Actions workflow to build a debug APK

Not implemented yet:
- Real dictionaries and autocorrection
- Hindi/Telugu input or transliteration
- AI grammar correction, rewriting or translation
- Model downloads or local inference
- Production privacy hardening and release signing

Do not describe this starter as a complete AI keyboard.

## Build

Requirements:
- JDK 17
- Android SDK Platform 35
- Android Build Tools 35.0.0
- Gradle 8.9

From a machine or cloud runner with the Android SDK configured:

```bash
gradle assembleDebug
```

APK output:
`app/build/outputs/apk/debug/app-debug.apk`

GitHub Actions is configured to build this APK on pushes to `main` and on manual workflow runs. Download it from the workflow run's Artifacts section.

## Install and test

1. Install the debug APK on your Android phone.
2. Open OpenType AI Keyboard.
3. Tap **Open keyboard settings**.
4. Enable OpenType AI Keyboard.
5. Return to the app and tap **Choose OpenType keyboard**.
6. Select OpenType AI Keyboard.
7. Open a notes app and test basic English key input.

Android settings labels can differ by device manufacturer.

## Development order

1. Stabilize the IME and text editing behavior.
2. Add privacy-aware editor detection and disable learning in password fields.
3. Integrate English dictionary and suggestions.
4. Integrate Hindi and Telugu input/transliteration.
5. Add a local AI runtime and optional model downloads.
6. Add grammar correction with preview/accept/reject.
7. Add rewrite and translation.
8. Test, document dependencies and licenses, then prepare release signing.

## Privacy

This starter has no network permission and no analytics SDK. Future model downloads will require adding network access deliberately. Text typed into the keyboard must remain local unless the user explicitly enables an optional remote service.

## License

Project source is intended to be released under GPL-3.0-or-later. Before distributing, add the complete GPL license text and verify the licenses of all bundled dependencies, dictionaries, language data and model weights.
