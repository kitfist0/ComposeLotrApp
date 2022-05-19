buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Essential.Gradle}")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:${Versions.CodeStyle.Spotless}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.Kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.Essential.Kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.Di.Hilt}")
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            ktlint(Versions.CodeStyle.Ktlint).userData(
                mapOf(
                    "disabled_rules" to "no-wildcard-imports",
                )
            )
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }

        kotlinGradle {
            target("**/*.gradle.kts", "*.gradle.kts")
            ktlint(Versions.CodeStyle.Ktlint)
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    afterEvaluate {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xopt-in=kotlin.RequiresOptIn",
                    "-Xopt-in=kotlin.OptIn",
                )
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
