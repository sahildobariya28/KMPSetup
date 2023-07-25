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

## Manage Share Module [build.gradle](https://github.com/sahildobariya28/KMPSetup/blob/main/shared/build.gradle.kts)
```
plugins {
    ...
    id("org.jetbrains.compose")
}
```

```
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        ...
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
}
```

```
kotlin {
    ...
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {}
        }
        ...
    }
}
```

```
android {
    namespace = "com.phone.kmpsetup"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
```

## Manage Android Module [build.gradle](https://github.com/sahildobariya28/KMPSetup/blob/main/androidApp/build.gradle.kts)
```
plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}
```
```
kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}
```
```
android {
    namespace = "com.phone.kmpsetup.android"
    compileSdk = 33

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")


    defaultConfig {
        applicationId = "com.phone.kmpsetup.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
```
```
dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.preview)
    implementation(compose.uiTooling)

    implementation("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
    implementation("io.github.xxfast:decompose-router:0.2.1")
}
```

