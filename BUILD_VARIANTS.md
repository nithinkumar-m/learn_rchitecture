# Build Variants Configuration

## Overview
The project now has multiple build variants configured for different environments.

## Changes Made

### 1. Gradle Upgrade
- **Old Version**: Gradle 8.2
- **New Version**: Gradle 8.5
- **Reason**: Gradle 8.5 supports Java 21, fixing the compatibility issue

### 2. Android Gradle Plugin Update
- **Updated**: From 8.2.0 to 8.2.2 for better compatibility

### 3. Build Variants Added

#### Product Flavors (Environment)
Three product flavors based on environment:

##### Dev Environment
- **Application ID**: `com.ottapp.dev`
- **Version Suffix**: `-dev`
- **Base URL**: `https://dev-api.ottapp.com/`
- **Environment**: `DEV`

##### Int Environment
- **Application ID**: `com.ottapp.int`
- **Version Suffix**: `-int`
- **Base URL**: `https://int-api.ottapp.com/`
- **Environment**: `INT`

##### Prod Environment
- **Application ID**: `com.ottapp`
- **Version Suffix**: None
- **Base URL**: `https://api.ottapp.com/`
- **Environment**: `PROD`

#### Build Types
Two build types:

##### Debug
- Debuggable: Yes
- Minify: No
- Application ID Suffix: `.debug`
- Version Suffix: `-debug`

##### Release
- Debuggable: No
- Minify: Yes
- Shrink Resources: Yes
- ProGuard: Enabled

## Available Build Variants

The combination of flavors and build types creates 6 variants:

1. **devDebug** - Development environment, debug build
2. **devRelease** - Development environment, release build
3. **intDebug** - Integration environment, debug build
4. **intRelease** - Integration environment, release build
5. **prodDebug** - Production environment, debug build
6. **prodRelease** - Production environment, release build

## Using Build Variants

### In Android Studio
1. Open the "Build Variants" panel (View > Tool Windows > Build Variants)
2. Select the desired variant from the dropdown

### Command Line

#### Build specific variant:
```bash
./gradlew assembleDevDebug
./gradlew assembleIntRelease
./gradlew assembleProdRelease
```

#### Generate APK:
```bash
./gradlew assembleDevDebug
# APK location: app/build/outputs/apk/dev/debug/
```

#### Generate App Bundle:
```bash
./gradlew bundleDevRelease
./gradlew bundleIntRelease
./gradlew bundleProdRelease
# Bundle location: app/build/outputs/bundle/
```

#### Install on device:
```bash
./gradlew installDevDebug
./gradlew installIntRelease
```

#### List all available tasks:
```bash
./gradlew tasks
```

## Accessing Build Config in Code

You can access the environment-specific values in your Kotlin code:

```kotlin
import com.ottapp.BuildConfig

// Access base URL
val baseUrl = BuildConfig.BASE_URL

// Access environment name
val environment = BuildConfig.ENVIRONMENT

// Check if debug build
val isDebug = BuildConfig.DEBUG
```

## Example: Configuring Retrofit with Build Variants

Update your `NetworkModule.kt`:

```kotlin
object NetworkModule {
    private const val BASE_URL = BuildConfig.BASE_URL
    
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
```

## Application IDs by Variant

This allows you to install multiple variants on the same device:

- **Dev Debug**: `com.ottapp.dev.debug`
- **Dev Release**: `com.ottapp.dev`
- **Int Debug**: `com.ottapp.int.debug`
- **Int Release**: `com.ottapp.int`
- **Prod Debug**: `com.ottapp.debug`
- **Prod Release**: `com.ottapp`

## Notes

- Each flavor has its own `BASE_URL` that can be used to point to different API endpoints
- You can install all variants on the same device simultaneously (they have different package names)
- Use Dev for daily development and testing
- Use Int for integration testing with backend teams
- Use Prod for production releases
- Release builds are optimized with ProGuard and resource shrinking
