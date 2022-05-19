import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 24
    const val targetSdk = 31
    const val compileSdk = 31
    const val jvmTarget = "11"
    const val versionCode = 1
    const val versionName = "1.0.0"

    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}

object Versions {
    object Essential {
        const val Kotlin = "1.6.10"
        const val Coroutines = "1.6.0"
        const val Serialization = "1.3.2"
        const val Gradle = "7.1.3"
        const val Ktx = "1.7.0"
    }

    object CodeStyle {
        const val Spotless = "6.6.1"
        const val Ktlint = "0.45.2"
    }

    object Compose {
        const val Master = "1.1.1"
        const val Accompanist = "0.24.7-alpha"
        const val Activity = "1.4.0"
        const val Lifecycle = "2.4.1"
        const val Navigation = "2.4.2"
        const val HiltNavigation = "1.0.0"
        const val Material3 = "1.0.0-alpha10"
    }

    object Network {
        const val OkHttp = "4.9.3"
        const val Retrofit = "2.9.0"
        const val Converter = "0.8.0"
    }

    object Di {
        const val Hilt = "2.41"
    }

    object Ui {
        const val Material3 = "1.6.0"
    }

    object Test {
        const val Kotest = "5.3.0"
        const val Mockk = "1.12.2"
    }
}

object Dependencies {
    const val hilt = "com.google.dagger:hilt-android:${Versions.Di.Hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Di.Hilt}"

    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.Ktx}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}",
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Essential.Serialization}",
    )

    val compose = listOf(
        "androidx.compose.ui:ui:${Versions.Compose.Master}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.material3:material3:${Versions.Compose.Material3}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.compose.runtime:runtime-livedata:${Versions.Compose.Master}",
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.Lifecycle}",
        "androidx.navigation:navigation-compose:${Versions.Compose.Navigation}",
        "androidx.hilt:hilt-navigation-compose:${Versions.Compose.HiltNavigation}",
        "com.google.accompanist:accompanist-insets:${Versions.Compose.Accompanist}",
    )

    val retrofit = listOf(
        "com.squareup.okhttp3:okhttp:${Versions.Network.OkHttp}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}",
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}",
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Network.Converter}",
    )

    val ui = listOf(
        "com.google.android.material:material:${Versions.Ui.Material3}",
    )

    val testing = listOf(
        "io.mockk:mockk:${Versions.Test.Mockk}",
        "io.mockk:mockk-agent-jvm:${Versions.Test.Mockk}",
        "io.kotest:kotest-runner-junit5:${Versions.Test.Kotest}",
        "io.kotest:kotest-assertions-core:${Versions.Test.Kotest}",
        "io.kotest:kotest-property:${Versions.Test.Kotest}",
        "org.jetbrains.kotlin:kotlin-reflect:${Versions.Essential.Kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Essential.Coroutines}",
    )
}
