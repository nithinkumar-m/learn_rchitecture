# 🚀 Quick Start Guide

Get your OTT app up and running in minutes!

## ⚡ Fast Setup (3 Steps)

### 1️⃣ Update API URL

Open `app/src/main/java/com/ottapp/core/network/NetworkModule.kt`

Replace this line:
```kotlin
private const val BASE_URL = "https://raw.githubusercontent.com/yourusername/ottapp-data/main/"
```

With your actual GitHub raw URL or API endpoint.

### 2️⃣ Upload Mock Data

- Upload `mock-api/home.json` to your GitHub repository
- Make sure the path matches your BASE_URL
- Test the URL in a browser to ensure it's accessible

### 3️⃣ Build & Run

```bash
# Open in Android Studio
# OR use command line:

./gradlew build
./gradlew installDebug
```

That's it! 🎉

## 📋 Pre-flight Checklist

Before running:
- [ ] BASE_URL is updated in NetworkModule.kt
- [ ] home.json is uploaded and accessible
- [ ] Android Studio is installed
- [ ] Android SDK 34 is installed
- [ ] Device/Emulator is connected
- [ ] Internet connection is available

## 🎯 Expected Result

After launching the app:

**0-2s**: Splash screen with animated logo
**2s+**: Home screen with:
- Hero carousel (auto-scrolling)
- Trending content (horizontal scroll)
- Multiple content sections
- Bottom navigation bar

**Navigation works**: Tap Home, Search, Library, Profile

## 🧪 Quick Test

### Test with Placeholder Images

If you want to test immediately without custom images, the provided `mock-api/home.json` already uses Unsplash images that should work out of the box!

Just upload it and you're ready to go.

## 🔧 Troubleshooting

### API Not Loading?

**Check these in order:**

1. **BASE_URL correct?**
   ```kotlin
   // In NetworkModule.kt
   private const val BASE_URL = "https://raw.githubusercontent.com/..."
   ```

2. **Internet permission?**
   ```xml
   <!-- In AndroidManifest.xml -->
   <uses-permission android:name="android.permission.INTERNET" />
   ```

3. **JSON accessible?**
   - Copy your JSON URL
   - Paste in browser
   - Should display JSON content

4. **Check Logcat:**
   ```
   Filter: "OkHttp" or "Retrofit"
   Look for network errors
   ```

### Build Errors?

```bash
# Clean and rebuild
./gradlew clean
./gradlew build

# Or in Android Studio:
# Build > Clean Project
# Build > Rebuild Project
```

### Gradle Issues?

```bash
# Update Gradle wrapper
./gradlew wrapper --gradle-version=8.2

# Or download manually
# File > Project Structure > Project > Gradle Version
```

## 📱 Testing on Different Devices

### Emulator (Recommended for first run)
```
Tools > Device Manager > Create Device
- Pixel 6 Pro
- API 34 (Android 14)
- Google APIs
```

### Physical Device
1. Enable Developer Options
2. Enable USB Debugging
3. Connect via USB
4. Trust the computer
5. Select device in Android Studio

## 🎨 Quick Customization

### Change App Name
`app/src/main/res/values/strings.xml`
```xml
<string name="app_name">My OTT App</string>
```

### Change Primary Color
`app/src/main/java/com/ottapp/ui/theme/Color.kt`
```kotlin
val PrimaryRed = Color(0xFFE50914) // Your color here
```

### Change App Icon
Replace files in:
- `app/src/main/res/mipmap-*/`
- Use Android Studio's Image Asset tool

## 📊 Verify Setup

Run these checks:

✅ App builds without errors  
✅ App launches to splash screen  
✅ Navigates to home after 2s  
✅ Content loads from API  
✅ Images display correctly  
✅ Bottom navigation works  
✅ All tabs are accessible  

## 🎓 Next Steps

Once your app is running:

1. **Explore the code**
   - Check `presentation/home/` for UI
   - Check `core/mvi/` for architecture
   - Check `data/` for models

2. **Read the docs**
   - README.md - Full overview
   - FEATURES.md - Feature details
   - API_DOCUMENTATION.md - API specs

3. **Customize**
   - Add your own content
   - Modify templates
   - Change colors/theme
   - Add new features

4. **Extend**
   - Add video player
   - Implement search
   - Add authentication
   - Build detail screens

## 💡 Tips

### Development
- Use hot reload for quick iterations
- Check Logcat for debugging
- Use Layout Inspector for UI debugging

### Testing
- Test on multiple screen sizes
- Test with slow network
- Test error states (disconnect internet)
- Test different Android versions

### Performance
- Monitor memory usage
- Check network requests
- Optimize images
- Profile with Android Profiler

## 🆘 Still Having Issues?

1. **Check the logs**
   ```
   View > Tool Windows > Logcat
   Filter by package: com.ottapp
   ```

2. **Verify dependencies**
   ```bash
   ./gradlew dependencies
   ```

3. **Check Android Studio version**
   - Minimum: Hedgehog (2023.1.1)
   - Recommended: Latest stable

4. **Review documentation**
   - README.md
   - SETUP_GUIDE.md
   - PROJECT_STRUCTURE.md

5. **Common fixes**
   ```bash
   # Invalidate caches
   File > Invalidate Caches / Restart
   
   # Re-sync Gradle
   File > Sync Project with Gradle Files
   
   # Clean build
   Build > Clean Project
   ```

## 📞 Need Help?

- Check existing issues in repository
- Create a new issue with:
  - Android Studio version
  - Error logs
  - Steps to reproduce
  - Expected vs actual behavior

## ✅ Success!

If you see content loading on the home screen, congratulations! 🎉

Your OTT app is now running. Start exploring and building!

---

**Happy Coding!** 🚀
