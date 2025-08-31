import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
    alias(libs.plugins.kover)
}
apply(from = "$rootDir/githooks.gradle")

hilt {
    enableAggregatingTask = true
}

kover {
    reports {
        filters {
            excludes {
                androidGeneratedClasses()
            }
        }
        verify {
            rule {
                bound {
                    minValue.set(80)
                    coverageUnits.set(CoverageUnit.INSTRUCTION)
                    aggregationForGroup.set(AggregationType.COVERED_PERCENTAGE)
                }
            }
        }
    }
}

android {
    namespace = "com.rahul.newsapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rahul.newsapp"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testApplicationId = "com.rahul.newsapp.testing"
        testInstrumentationRunner = "com.rahul.newsapp.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"82d40fffbbdf4ee2bfa542808ccec60c\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"82d40fffbbdf4ee2bfa542808ccec60c\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
composeCompiler {
    featureFlags.addAll(
        ComposeFeatureFlag.StrongSkipping,
        ComposeFeatureFlag.OptimizeNonSkippingGroups
    )
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.constraintlayout)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    //Navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.runtime.compose)

    //Retrofit for networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //Room Database dependencies
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    //optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.ktx)
    //Custom Tabs
    implementation(libs.browser)
    //Lottie
    implementation(libs.lottie.compose)
    // Paging 3
    implementation(libs.paging)
    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.core.testing)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.rules)
//    androidTestImplementation(libs.uiautomator)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.androidx.activity.compose)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
}