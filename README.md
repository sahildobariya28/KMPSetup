# KMPSetup
# How to Setup Project

## Add Plugins in build.gradle (project)
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

## Add properties in gradle.properties
  ``` 
  #MPP
  kotlin.mpp.stability.nowarn=true
  #Compose
  org.jetbrains.compose.experimental.uikit.enabled=true
  kotlin.native.cacheKind=none

  #Versions
  kotlin.version=1.8.20
  agp.version=8.0.1
  compose.version=1.4.0

  #macOS
  org.jetbrains.compose.experimental.jscanvas.enabled=true
  org.jetbrains.compose.experimental.macos.enabled=true
  ```

## 
