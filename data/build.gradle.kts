plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "dev.lotr.data"

    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        compileSdk = Application.compileSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    Dependencies.essential.forEach(::implementation)
    Dependencies.retrofit.forEach(::implementation)
}
