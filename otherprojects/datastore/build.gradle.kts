plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.me.harris.datastore"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.12"
//    }

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {

    implementation(libs.androidx.core.core.ktx)
    implementation(libs.androidx.appcompat.appcompat)
    implementation(libs.google.android.material.material)
    implementation(libs.androidx.datastore.preference)
    implementation(libs.androidx.datastore.core)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)
    implementation (libs.coroutines.core)
    implementation (libs.coroutines.android)

    implementation(platform(libs.androidx.compose.bom))
//    implementation (libs.androidx.compiler)
    implementation("androidx.activity:activity-compose")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.animation:animation-android")
    implementation("androidx.compose.animation:animation-core")
    implementation("androidx.compose.animation:animation-core-android")
    implementation("androidx.compose.animation:animation-graphics")
    implementation("androidx.compose.animation:animation-graphics-android")
    implementation("androidx.compose.foundation:foundation")
//    implementation("androidx.compose.material:material")
//    implementation("androidx.compose.material:material-android")
//    implementation("androidx.compose.material:material-ripple")
//    implementation("androidx.compose.material:material-icons-core")
//    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.runtime:runtime-saveable")
    implementation("androidx.compose.foundation:foundation-layout")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-android")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-unit")
    implementation("androidx.compose.ui:ui-geometry")
    implementation("androidx.compose.ui:ui-text")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
}