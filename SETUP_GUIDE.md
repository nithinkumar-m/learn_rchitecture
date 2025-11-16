# Quick Setup Guide

## 🚀 Getting Started

### Step 1: Configure API Endpoint

The app needs JSON data to display content. You have two options:

#### Option A: Use GitHub (Recommended for testing)

1. Create a new GitHub repository (or use an existing one)
2. Upload the `mock-api/home.json` file to your repository
3. Get the raw URL (e.g., `https://raw.githubusercontent.com/username/repo/main/home.json`)
4. Update the BASE_URL in `app/src/main/java/com/ottapp/core/network/NetworkModule.kt`:

```kotlin
private const val BASE_URL = "https://raw.githubusercontent.com/YOUR_USERNAME/YOUR_REPO/main/mock-api/"
```

#### Option B: Use a Mock API Service

Alternatively, you can use services like:
- MockAPI.io
- JSONPlaceholder
- Your own backend server

Just make sure the JSON structure matches the `HomeResponse` model.

### Step 2: Sync Gradle

1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. If prompted, update Gradle or SDK versions

### Step 3: Build & Run

```bash
# Build the project
./gradlew build

# Install on device/emulator
./gradlew installDebug
```

Or simply click the "Run" button in Android Studio.

## 📱 Testing the App

### Expected Behavior:

1. **Splash Screen** (2 seconds)
   - Animated logo with scale animation
   - Automatic navigation to Home

2. **Home Screen**
   - Hero carousel at top (auto-scrolling every 5 seconds)
   - Multiple content sections
   - Different templates (horizontal lists, grids, banners)
   - Bottom navigation bar

3. **Navigation**
   - Home: Main content feed
   - Search: Search interface (placeholder)
   - Library: User's library (placeholder)
   - Profile: User profile and settings (placeholder)

## 🔧 Customization

### Change App Colors

Edit `app/src/main/java/com/ottapp/ui/theme/Color.kt`:

```kotlin
val PrimaryRed = Color(0xFFE50914) // Main brand color
val DarkBackground = Color(0xFF141414) // Background
val CardBackground = Color(0xFF2F2F2F) // Card backgrounds
```

### Modify Templates

Templates are in `app/src/main/java/com/ottapp/presentation/home/components/`:
- `HeroCarouselTemplate.kt` - Main banner carousel
- `HorizontalListTemplate.kt` - Scrolling content rows
- `VerticalGridTemplate.kt` - Grid layouts
- `FeaturedBannerTemplate.kt` - Featured content banners

### Add New Navigation Items

1. Add route in `Screen.kt`
2. Create new screen composable
3. Add to `NavigationGraph.kt`
4. Add item to `bottomNavItems` in `BottomNavigationBar.kt`

## 🐛 Troubleshooting

### Common Issues:

1. **API not loading**
   - Check internet connection
   - Verify BASE_URL is correct
   - Check Logcat for network errors
   - Ensure JSON structure matches models

2. **Build errors**
   - Clean project: `Build > Clean Project`
   - Invalidate caches: `File > Invalidate Caches / Restart`
   - Check SDK versions match your setup

3. **Images not loading**
   - Verify image URLs are accessible
   - Check internet permissions in AndroidManifest
   - Try placeholder images first

4. **Gradle sync fails**
   - Update Android Studio to latest version
   - Check internet connection
   - Verify JDK 17 is installed

## 📊 JSON Data Structure

Your `home.json` should follow this structure:

```json
{
  "collections": [
    {
      "id": "unique-id",
      "title": "Section Title",
      "template": "hero_carousel", // or horizontal_list, vertical_grid, featured_banner
      "items": [
        {
          "id": "item-id",
          "title": "Content Title",
          "description": "Description text",
          "imageUrl": "https://...",
          "thumbnailUrl": "https://...",
          "type": "movie", // or series, video, live
          "duration": "2h 30m",
          "rating": 8.5,
          "year": 2024,
          "genres": ["Action", "Drama"]
        }
      ]
    }
  ]
}
```

## 🎨 Design Guidelines

- Use high-quality images (800x450 for hero, 300x450 for thumbnails)
- Keep descriptions under 150 characters for best display
- Use real or placeholder images from services like:
  - Unsplash (https://unsplash.com)
  - Pexels (https://pexels.com)
  - Lorem Picsum (https://picsum.photos)

## 🚀 Next Steps

1. Implement video player
2. Add content detail screens
3. Build search functionality
4. Add user authentication
5. Implement favorites/watchlist
6. Add offline support

## 📚 Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [MVI Architecture](https://hannesdorfmann.com/android/model-view-intent/)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Material Design 3](https://m3.material.io/)

---

Need help? Check the main README.md or create an issue!
