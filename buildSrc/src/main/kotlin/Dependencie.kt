object Plugin {
    val android by lazy { "com.android.tools.build:gradle:${Version.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}" }
    val googleService by lazy { "com.google.gms:google-services:${Version.googleService}" }
    val firebaseCrashlytics by lazy { "com.google.firebase:firebase-crashlytics-gradle:${Version.firebaseCrashlytics}" }
    val navigationSafeArgs by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigationSafeArgs}" }
}

object App {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Version.appCompat}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Version.fragmentKtx}" }
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Version.navigationFragmentKtx}" }
    val navigationUIKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Version.navigationUIKtx}" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Version.activityKtx}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Version.coreKtx}" }
    val materialUi by lazy { "com.google.android.material:material:${Version.materialUi}" }
    val legacySupportV4 by lazy { "androidx.legacy:legacy-support-v4:${Version.legacySupportV4}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}" }
    val swipeRefresh by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0" }

    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesCore}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}" }

    val retrofit2 by lazy { "com.squareup.retrofit2:retrofit:${Version.retrofit2}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Version.okHttp}" }

    val securityCrypto by lazy { "androidx.security:security-crypto:${Version.securityCrypto}" }

    val permissionX by lazy { "com.guolindev.permissionx:permissionx:1.6.4" }

    val androidImagePicker by lazy { "com.github.esafirm:android-image-picker:3.0.0-beta5" }

    val fileCompressor by lazy { "id.zelory:compressor:2.1.1" }

    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Version.moshiKotlin}" }
    val moshi by lazy { "com.squareup.moshi:moshi:${Version.moshi}" }
    val converterMoshi by lazy { "com.squareup.retrofit2:converter-moshi:${Version.converterMoshi}" }
    val moshiKotlinCodegen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshiKotlinCodegen}" }

    val dagger by lazy { "com.google.dagger:dagger:${Version.dagger}" }
    val daggerCompiler by lazy { "com.google.dagger:dagger-compiler:${Version.daggerCompiler}" }

    val coil by lazy { "io.coil-kt:coil:${Version.coil}" }

    val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModelKtx}" }

    val chuck by lazy { "com.readystatesoftware.chuck:library:${Version.chuck}" }
    val chuckNoOp by lazy { "com.readystatesoftware.chuck:library-no-op:${Version.chuck}" }
    val stetho by lazy { "com.facebook.stetho:stetho:${Version.stetho}" }

    val firebaseBom by lazy { "com.google.firebase:firebase-bom:${Version.firebaseBom}" }
    val crashlyticsKtx by lazy { "com.google.firebase:firebase-crashlytics-ktx:18.2.12" }
    val analyticsKtx by lazy { "com.google.firebase:firebase-analytics-ktx:21.1.0" }

    val junit by lazy { "junit:junit:${Version.junit}" }
    val junitExt by lazy { "androidx.test.ext:junit:${Version.junitExt}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Version.espressoCore}" }
    val testRules by lazy { "androidx.test:rules:${Version.testRules}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Version.timber}" }
}