PhotoList App
A simple Android application that displays a grid of photos and allows you to view each photo in a detail screen with pinch-to-zoom and double-tap-to-zoom support. Built with Jetpack Compose, Material3, and Coil.

Features
* Home Screen: Displays a grid of photos.
* Detail Screen: Shows an enlarged view of the selected photo.
    * Pinch-to-Zoom: Use two fingers to zoom in/out.
    * Double-Tap to Zoom: Quickly double-tap to toggle zoom levels.
* Navigation: Uses the Jetpack Navigation Compose framework for routing between screens.
* Architecture: Follows a basic MVVM approach with a ViewModel that fetches and holds UI state.
* Edge-to-Edge UI: Adopts a modern layout that seamlessly uses the device’s full screen.

Getting Started
Prerequisites
* Android Studio (Giraffe or later recommended).
* Kotlin 1.8+.
* Gradle 8.0+.
* A device or emulator running Android 5.0 (API 21) or higher.
  

Cloning the Repository
bash
CopyEdit
git clone https://github.com/yeshwanthcshekar/SampleApps>.git
Then open the project in Android Studio.

Building and Running
1. Open the Project in Android Studio.
2. Select a run configuration (an emulator or connected device).
3. Click the Run button or use Shift + F10 (Windows) / Control + R (macOS).

app
├── manifests
│   └── AndroidManifest.xml
├── java/com/example/photolist  (or kotlin/com/example/photolist)
│   ├── data
│   │   ├── AppContainer.kt
│   │   └── PhotosRepository.kt
│   ├── navigation
│   │   ├── NavigationDestination.kt
│   │   └── PhotoNavGraph.kt
│   ├── network
│   │   └── PhotosApiService.kt
│   └── ui
│       └── theme
│           ├── screens
│           │   ├── Color.kt
│           │   ├── PhotosApp.kt
│           │   ├── PhotosApplication.kt
│           │   ├── Theme.kt
│           │   ├── Type.kt
│           │   └── MainActivity.kt
│
├── res
│   ├── drawable/
│   ├── mipmap/
│   ├── values/
│   │   ├── colors.xml
│   │   ├── strings.xml
│   │   └── themes.xml
│   └── xml/
│
├── test/ (unit tests)
└── androidTest/ (instrumented tests)


Key Files
* MainActivity.kt: Entry point of the app.
* PhotosApp.kt: Hosts the single Scaffold for the entire application.
* PhotoNavHost.kt: Sets up the navigation routes for home and detail screens.
* HomeScreen.kt: Displays the list of photos using a LazyVerticalGrid.
* PhotoDetailScreen.kt: Shows the selected photo, including a top bar and zoomable image.
* ZoomableImageWithCoil.kt: Composable that implements pinch-to-zoom and double-tap-to-zoom functionality using a custom ZoomState and the Coil library.
* PhotosViewModel.kt: Fetches and holds the UI state (list of photos).
* PhotosApplication.kt: Custom Application class that sets up any required app-wide dependencies (e.g., AppContainer).

How It Works
1. Home Screen
    * A LazyVerticalGrid displays photo thumbnails.
    * Selecting (tapping) a photo navigates to the Photo Detail.
2. Photo Detail
    * Renders a single photo using AsyncImage (Coil).
    * User can pinch to zoom or double-tap to toggle zoom between 1x and 2x.
3. Architecture
    * The app uses a lightweight MVVM approach:
        * ViewModel: PhotosViewModel manages fetching/loading photos.
        * UI State: PhotosUiState holds different states: Loading, Error, or Success with a list of photos.

Libraries & Tools
* Jetpack Compose: For modern, declarative UI.
* Material3 Components: For Material theming and components (top app bars, scaffolds, etc.).
* Coil: For asynchronous image loading.
* Navigation Compose: For handling in-app navigation.
