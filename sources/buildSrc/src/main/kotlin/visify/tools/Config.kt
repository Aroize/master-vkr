package visify.tools


import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 33

    val sourceCompat = JavaVersion.VERSION_17
    val targetCompat = JavaVersion.VERSION_17

    val kotlinJvmTarget = JvmTarget.JVM_17
    const val composeKotlinCompilerExtensionVersion = "1.5.10"
}