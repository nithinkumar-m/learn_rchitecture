# OTT App - Android Streaming Platform

A modern Android OTT (Over-The-Top) application built with Jetpack Compose, showcasing best practices in Android development with clean architecture and MVI pattern.

## 🚀 Features

- **Modern UI with Jetpack Compose**: Fully declarative UI built with Compose
- **MVI Architecture**: Unidirectional data flow with Intent-State-Effect pattern
- **Multiple Templates**: Dynamic template-based rendering system
  - Hero Carousel with auto-scroll
  - Horizontal scrolling lists
  - Vertical grid layouts
  - Featured banner sections
- **Bottom Navigation**: Seamless navigation between Home, Search, Library, and Profile
- **Splash Screen**: Animated splash screen with smooth transitions
- **Dark Theme**: Netflix-style dark theme optimized for video content
- **Kotlin Coroutines**: Asynchronous programming with structured concurrency
- **Image Loading**: Efficient image loading and caching with Coil

## 🏗️ Architecture

The app follows **Clean Architecture** principles with **MVI (Model-View-Intent)** pattern:

### Layers:

1. **Presentation Layer**
   - Compose UI components
   - ViewModels with MVI pattern
   - Screen states and effects

2. **Domain Layer**
   - Use cases (future implementation)
   - Business logic

3. **Data Layer**
   - Repository pattern
   - API services
   - Data models

### MVI Pattern:

```
User Action (Intent) → ViewModel → State Update → UI Recomposition
                           ↓
                      Side Effects (Navigation, Toasts, etc.)
```

## 📦 Tech Stack

- **Kotlin**: 100% Kotlin
- **Jetpack Compose**: Modern declarative UI
- **Material 3**: Latest Material Design components
- **Kotlin Coroutines**: Asynchronous programming
- **StateFlow**: State management
- **Retrofit**: REST API client
- **OkHttp**: HTTP client
- **Gson**: JSON parsing
- **Coil**: Image loading
- **Navigation Compose**: Type-safe navigation
- **Accompanist Pager**: Carousel implementation

## 🎨 UI Components

### Home Screen Templates:

1. **Hero Carousel**
   - Auto-scrolling banner
   - Large images with gradient overlay
   - Play button integration
   - Page indicators

2. **Horizontal List**
   - Scrollable content cards
   - Thumbnail images
   - Title and duration display

3. **Vertical Grid**
   - 3-column grid layout
   - Compact content display
   - Poster-style images

4. **Featured Banner**
   - Single highlighted content
   - Large banner with description
   - Call-to-action button

## 📱 Screens

1. **Splash Screen**: Animated app intro
2. **Home Screen**: Main content feed with multiple templates
3. **Search Screen**: Content search functionality (placeholder)
4. **Library Screen**: User's saved content (placeholder)
5. **Profile Screen**: User settings and preferences

## 🔧 Setup Instructions

### Prerequisites:
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 34
- Minimum SDK 24 (Android 7.0)

### Steps:

1. **Clone the repository**
```bash
git clone <repository-url>
cd OTTApp
```

2. **Update API Endpoint**
   - Open `app/src/main/java/com/ottapp/core/network/NetworkModule.kt`
   - Update `BASE_URL` with your GitHub raw content URL or API endpoint
   
```kotlin
private const val BASE_URL = "https://raw.githubusercontent.com/yourusername/ottapp-data/main/"
```

3. **Setup Mock Data**
   - Upload the `mock-api/home.json` file to a GitHub repository
   - Or use any other hosting service that provides raw JSON
   - The JSON structure should match the `HomeResponse` model

4. **Build the project**
```bash
./gradlew build
```

5. **Run the app**
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio or use:
```bash
./gradlew installDebug
```

## 📊 Data Model

The app uses a template-based content system:

```kotlin
HomeResponse
  └── ContentCollection
       ├── id: String
       ├── title: String
       ├── template: TemplateType (hero_carousel, horizontal_list, etc.)
       └── items: List<ContentItem>
            ├── id, title, description
            ├── imageUrl, thumbnailUrl
            ├── type (movie, series, video, live)
            ├── duration, rating, year
            └── genres
```

### Template Types:
- `hero_carousel`: Large auto-scrolling banners
- `horizontal_list`: Horizontal scrolling content rows
- `vertical_grid`: Grid layout for more content
- `featured_banner`: Single highlighted content

## 🔄 State Management

The app uses MVI pattern with the following components:

```kotlin
// User actions
sealed class HomeIntent : ViewIntent {
    object LoadContent
    data class ContentItemClicked(val itemId: String)
    object Retry
}

// UI state
data class HomeState(
    val isLoading: Boolean,
    val collections: List<ContentCollection>,
    val error: String?
)

// One-time effects
sealed class HomeEffect : ViewEffect {
    data class NavigateToDetail(val itemId: String)
    data class ShowError(val message: String)
}
```

## 🎯 Future Enhancements

- [ ] Video player integration
- [ ] Content detail screen
- [ ] User authentication
- [ ] Favorites/Watchlist functionality
- [ ] Search implementation with filters
- [ ] Offline caching
- [ ] Download management
- [ ] Continue watching feature
- [ ] Multi-profile support
- [ ] Parental controls
- [ ] Cast to TV support
- [ ] Picture-in-Picture mode

## 🧪 Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## 📄 License

This project is for educational purposes.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Contact

For any queries or suggestions, please open an issue in the repository.

---

**Built with ❤️ using Jetpack Compose**
