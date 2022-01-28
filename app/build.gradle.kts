plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        compileSdk = Application.compileSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }

    sourceSets {
        getByName("main").run {
            java.srcDirs("src/main/kotlin")
        }
    }

    compileOptions {
        sourceCompatibility = Application.sourceCompat
        targetCompatibility = Application.targetCompat
    }

    kotlinOptions {
        jvmTarget = Application.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.Master
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    Dependencies.essential.forEach(::implementation)
    Dependencies.compose.forEach(::implementation)
    Dependencies.retrofit.forEach(::implementation)
    Dependencies.ui.forEach(::implementation)

    Dependencies.testing.forEach(::testImplementation)
}
