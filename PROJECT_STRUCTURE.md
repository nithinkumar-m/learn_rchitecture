# OTT App - Project Structure

## 📁 Directory Overview

```
OTTApp/
├── app/                                    # Main application module
│   ├── src/
│   │   └── main/
│   │       ├── java/com/ottapp/
│   │       │   ├── core/                   # Core utilities and base classes
│   │       │   │   ├── mvi/               # MVI architecture base
│   │       │   │   │   └── MviViewModel.kt
│   │       │   │   └── network/           # Network configuration
│   │       │   │       ├── ApiService.kt
│   │       │   │       └── NetworkModule.kt
│   │       │   │
│   │       │   ├── data/                   # Data layer
│   │       │   │   ├── model/             # Data models
│   │       │   │   │   └── Models.kt
│   │       │   │   └── repository/        # Repositories
│   │       │   │       └── HomeRepository.kt
│   │       │   │
│   │       │   ├── presentation/          # UI layer
│   │       │   │   ├── home/             # Home feature
│   │       │   │   │   ├── components/   # Reusable home components
│   │       │   │   │   │   ├── HeroCarouselTemplate.kt
│   │       │   │   │   │   ├── HorizontalListTemplate.kt
│   │       │   │   │   │   ├── VerticalGridTemplate.kt
│   │       │   │   │   │   └── FeaturedBannerTemplate.kt
│   │       │   │   │   ├── HomeContract.kt
│   │       │   │   │   ├── HomeScreen.kt
│   │       │   │   │   └── HomeViewModel.kt
│   │       │   │   │
│   │       │   │   ├── search/           # Search feature
│   │       │   │   │   └── SearchScreen.kt
│   │       │   │   │
│   │       │   │   ├── library/          # Library feature
│   │       │   │   │   └── LibraryScreen.kt
│   │       │   │   │
│   │       │   │   ├── profile/          # Profile feature
│   │       │   │   │   └── ProfileScreen.kt
│   │       │   │   │
│   │       │   │   ├── splash/           # Splash screen
│   │       │   │   │   └── SplashScreen.kt
│   │       │   │   │
│   │       │   │   └── navigation/       # Navigation components
│   │       │   │       ├── Screen.kt
│   │       │   │       ├── NavigationGraph.kt
│   │       │   │       └── BottomNavigationBar.kt
│   │       │   │
│   │       │   ├── ui/theme/             # App theming
│   │       │   │   ├── Color.kt
│   │       │   │   ├── Theme.kt
│   │       │   │   └── Type.kt
│   │       │   │
│   │       │   ├── MainActivity.kt       # Main activity
│   │       │   └── OTTApplication.kt     # Application class
│   │       │
│   │       ├── res/                       # Resources
│   │       │   ├── drawable/
│   │       │   ├── mipmap-*/
│   │       │   └── values/
│   │       │       ├── colors.xml
│   │       │       ├── strings.xml
│   │       │       └── themes.xml
│   │       │
│   │       └── AndroidManifest.xml       # App manifest
│   │
│   ├── build.gradle.kts                   # Module build config
│   └── proguard-rules.pro                # ProGuard rules
│
├── mock-api/                              # Mock API data
│   └── home.json                         # Home screen data
│
├── gradle/                                # Gradle wrapper
│   └── wrapper/
│       └── gradle-wrapper.properties
│
├── build.gradle.kts                      # Project build config
├── settings.gradle.kts                   # Project settings
├── gradle.properties                     # Gradle properties
├── .gitignore                           # Git ignore rules
├── README.md                            # Project documentation
└── SETUP_GUIDE.md                       # Quick setup guide

```

## 🏗️ Architecture Layers

### 1. Core Layer (`core/`)
Contains base classes and utilities used across the app:
- **MVI**: Base ViewModel and interfaces for MVI pattern
- **Network**: Retrofit setup and API service definitions

### 2. Data Layer (`data/`)
Manages data sources and business logic:
- **Models**: Data classes representing API responses
- **Repository**: Abstract data access layer

### 3. Presentation Layer (`presentation/`)
UI components and screen logic:
- **Features**: Organized by feature (home, search, library, profile)
- **Components**: Reusable UI components
- **Navigation**: App navigation setup
- **Splash**: App entry screen

### 4. UI Layer (`ui/theme/`)
App-wide styling and theming:
- Colors, typography, and Material theme setup

## 🔄 Data Flow (MVI)

```
User Action → Intent → ViewModel → Repository → API
                ↓                        ↓
            State Update ← Result ← Response
                ↓
         UI Recomposition
```

## 📦 Key Components

### MVI Base Classes
- `MviViewModel<S, I, E>`: Base ViewModel with state and effect management
- `ViewState`: Marker interface for screen states
- `ViewIntent`: Marker interface for user actions
- `ViewEffect`: Marker interface for one-time events

### Home Feature
- **HomeContract**: Defines Intent, State, and Effect for home screen
- **HomeViewModel**: Handles business logic and state management
- **HomeScreen**: Main composable for home UI
- **Templates**: Different UI templates for content display

### Navigation
- **Screen**: Sealed class defining all app routes
- **NavigationGraph**: NavHost setup with all routes
- **BottomNavigationBar**: Bottom navigation UI

## 🎨 Template System

The app uses a flexible template system for rendering content:

1. **Hero Carousel**: Auto-scrolling banner for featured content
2. **Horizontal List**: Scrollable row of content cards
3. **Vertical Grid**: Grid layout for multiple items
4. **Featured Banner**: Single highlighted content section

Each template:
- Receives a `ContentCollection` with items
- Renders according to its specific layout
- Handles click events uniformly

## 🔧 Dependencies

### Core Android
- AndroidX Core KTX
- Activity Compose
- Lifecycle Runtime

### Jetpack Compose
- Compose UI
- Material 3
- Navigation Compose
- ViewModel Compose

### Networking
- Retrofit
- OkHttp
- Gson

### Image Loading
- Coil Compose

### UI Helpers
- Accompanist Pager (for carousels)

### Coroutines
- Kotlinx Coroutines Android
- Kotlinx Coroutines Core

## 📝 Naming Conventions

### Files
- Screens: `XxxScreen.kt`
- ViewModels: `XxxViewModel.kt`
- Contracts: `XxxContract.kt`
- Components: `XxxTemplate.kt` or `XxxComponent.kt`

### Classes
- Composables: PascalCase (e.g., `HomeScreen`)
- ViewModels: PascalCase with `ViewModel` suffix
- Data classes: PascalCase (e.g., `ContentItem`)
- Sealed classes: PascalCase (e.g., `HomeIntent`)

### Functions
- Composables: PascalCase (e.g., `HeroCarousel()`)
- Regular functions: camelCase (e.g., `loadContent()`)

### Resources
- Strings: snake_case (e.g., `app_name`)
- Colors: snake_case (e.g., `primary_red`)
- Drawables: snake_case (e.g., `ic_launcher`)

## 🚀 Build Variants

Currently supports:
- **Debug**: Development build with logging
- **Release**: Production build with ProGuard

## 🧪 Testing Structure (Future)

```
app/src/
├── test/                 # Unit tests
│   └── java/
└── androidTest/          # Instrumented tests
    └── java/
```

## 📱 Minimum Requirements

- Android 7.0 (API 24)
- Target: Android 14 (API 34)
- Compile: Android 14 (API 34)

## 🎯 Key Features by Layer

### Presentation Layer Features
✅ Splash screen with animation
✅ Bottom navigation
✅ Template-based rendering
✅ Hero carousel with auto-scroll
✅ Multiple content sections
✅ Error and loading states

### Data Layer Features
✅ Repository pattern
✅ Network error handling
✅ Coroutine-based async operations

### Core Layer Features
✅ MVI architecture base
✅ State management
✅ Effect handling
✅ Network configuration

## 🔜 Future Additions

Potential directories for future features:
- `domain/` - Use cases and business logic
- `di/` - Dependency injection (Dagger/Hilt)
- `util/` - Utility classes and extensions
- `cache/` - Local data storage (Room)
- `player/` - Video player integration

---

This structure follows Android's recommended app architecture and best practices for scalability and maintainability.
