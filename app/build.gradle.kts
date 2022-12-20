plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Android.compileSdk
    buildToolsVersion = Android.buildTools

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // The following argument makes the Android Test Orchestrator run its
        // "pm clear" command after each test invocation. This command ensures
        // that the app's state is completely cleared between tests.
        testInstrumentationRunnerArguments += mapOf("clearPackageData" to "true")

        buildConfigField("String", "API_URL", "\"https://api.imgflip.com/\"")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(App.appCompat)
    implementation(App.coreKtx)
    implementation(App.constraintLayout)
    implementation(App.viewModelKtx)
    implementation(App.activityKtx)
    implementation(App.fragmentKtx)
    implementation(App.swipeRefresh)
    implementation(App.materialUi)

    implementation(App.retrofit2)
    implementation(App.loggingInterceptor)
    implementation(App.okhttp)

    implementation(App.permissionX)
    implementation(App.androidImagePicker)
    implementation(App.fileCompressor)

    implementation(App.dagger)
    kapt(App.daggerCompiler)

    implementation(App.coil)

    implementation(App.coroutinesCore)
    implementation(App.coroutinesAndroid)

    implementation(App.moshiKotlin)
    implementation(App.moshi)
    implementation(App.converterMoshi)
    kapt(App.moshiKotlinCodegen)

    testImplementation(App.junit)
    androidTestImplementation(App.espressoCore)
    androidTestImplementation(App.junitExt)
}