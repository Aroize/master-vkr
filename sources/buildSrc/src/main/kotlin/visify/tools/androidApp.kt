package visify.tools

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File

fun Project.androidApp(
    pkg: String,
    code: Int,
    version: String,
    useCompose: Boolean,
    signings: List<Signing>,
    variants: List<Variant>,
    deps: DependencyHandlerScope.() -> Unit,
    placeholders: Map<String, String> = emptyMap(),
    plugins: List<String> = emptyList(),
    configure: Project.() -> Unit = {  }
) {

    apply(plugin = "com.android.application")
    apply(plugin = "org.jetbrains.kotlin.android")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    plugins.forEach { id ->
        apply(plugin = id)
    }

    configure()

    configure<BaseAppModuleExtension> {
        namespace = pkg
        compileSdk = Config.compileSdk

        buildFeatures {
            buildConfig = true
            compose = useCompose
        }

        defaultConfig {
            applicationId = pkg
            minSdk = Config.minSdk
            targetSdk = Config.targetSdk

            versionCode = code
            versionName = version

            multiDexEnabled = true

            vectorDrawables { useSupportLibrary = true }

            placeholders.forEach { (key, value) ->
                manifestPlaceholders[key] = value
            }

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        signingConfigs {
            signings.forEach { signing ->
                maybeCreate(signing.type).let { config ->
                    config.storeFile = File(tools, signing.storeFilename)
                    config.keyAlias = signing.alias
                    config.keyPassword = signing.password
                }
            }
        }

        buildTypes {
            variants.forEach { variant ->
                maybeCreate(variant.name).let { type ->
                    type.isMinifyEnabled = variant.isMinifyEnabled
                    type.proguardFiles.addAll(
                        listOf(
                            optimizeFile,
                            file(variant.projectRelativeProguard)
                        ),
                    )
                }
            }
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = Config.sourceCompat
            targetCompatibility = Config.targetCompat
        }

        composeOptions {
            kotlinCompilerExtensionVersion = Config.composeKotlinCompilerExtensionVersion
        }

        (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
            jvmTarget = Config.kotlinJvmTarget.target
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {
        deps()
    }

//    detekt {
//        source = files(projectDir)
//        config = files("${project.rootDir}/config/detekt/detekt.yml")
//        parallel = true
//    }
}