import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ru.hse.glimpse"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.hse.glimpse"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

tasks {
    register<Exec>("buildAndSendApkToTelegram") {
        dependsOn("assembleDebug")

        val secretProperties = Properties()
        val secretPropertiesFile = file("/Users/pa.medvedev/AndroidStudioProjects/glimpse/secret.properties")
        if (secretPropertiesFile.exists()) {
            secretProperties.load(FileInputStream(secretPropertiesFile))
        }

        val botToken = secretProperties["botToken"]
        val chatID = secretProperties["chatID"]
        val filePath = layout.projectDirectory.dir("./build/outputs/apk/debug/app-debug.apk").asFile.path

        commandLine(
            "curl",
            "-F", "document=@$filePath",
            "https://api.telegram.org/bot$botToken/sendDocument?chat_id=$chatID"
        )
//        commandLine("echo", "document=@$filePath")
    }
}

dependencies {

    // navigation
    implementation("com.github.terrakok:cicerone:7.1")

    // kotea
    implementation("ru.tinkoff.kotea:core:1.1.0")
    implementation("ru.tinkoff.kotea:android:1.1.0")

    // viewbinding
    implementation("com.github.kirich1409:viewbindingpropertydelegate-full:1.5.9")

    // di
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    // android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}

kapt {
    correctErrorTypes = true
}
