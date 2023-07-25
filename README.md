# KMPSetup
# How to Setup Project

## Add Plugins in [build.gradle(project)](https://github.com/sahildobariya28/KMPSetup/blob/main/build.gradle.kts)
  ``` 
  plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
  }
  ```

## Add properties & version in [gradle.properties](https://github.com/sahildobariya28/KMPSetup/blob/main/gradle.properties)
  ``` 
  #MPP
  kotlin.mpp.stability.nowarn=true

  #Compose
  org.jetbrains.compose.experimental.uikit.enabled=true
  kotlin.native.cacheKind=none

  #macOS
  org.jetbrains.compose.experimental.jscanvas.enabled=true
  org.jetbrains.compose.experimental.macos.enabled=true

  #Versions
  kotlin.version=1.8.20
  agp.version=8.0.1
  compose.version=1.4.0
  ```

## Check and Update Wrapper Properties [gradle-wrapper.properties](https://github.com/sahildobariya28/KMPSetup/blob/main/gradle/wrapper/gradle-wrapper.properties)
  ```
  distributionUrl=https\://services.gradle.org/distributions/gradle-8.0-bin.zip
  ```

## Manage Repository, Plugin & Module [settings.gradle.kts](https://github.com/sahildobariya28/KMPSetup/blob/main/settings.gradle.kts)
  ```
  pluginManagement {
      repositories {
        ...
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
      }
  }
  dependencyResolutionManagement {
      repositories {
          ...
          maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
          maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
      }
  }
  ```
  
  ```
  pluginManagement {
      ...
      plugins {
          val kotlinVersion = extra["kotlin.version"] as String
          val agpVersion = extra["agp.version"] as String
          val composeVersion = extra["compose.version"] as String
  
          kotlin("jvm").version(kotlinVersion)
          kotlin("multiplatform").version(kotlinVersion)
          kotlin("android").version(kotlinVersion)
  
          id("com.android.application").version(agpVersion)
          id("com.android.library").version(agpVersion)
  
          id("org.jetbrains.compose").version(composeVersion)
      }
  }
  ```
