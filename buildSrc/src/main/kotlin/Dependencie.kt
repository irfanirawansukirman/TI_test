object Plugin {
    val android by lazy { "com.android.tools.build:gradle:${Version.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}" }
}

object App {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Version.appCompat}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Version.coreKtx}" }
    val materialUi by lazy { "com.google.android.material:material:${Version.materialUi}" }
    val swipeRefresh by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefresh}" }

    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesCore}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}" }

    val retrofit2 by lazy { "com.squareup.retrofit2:retrofit:${Version.retrofit2}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Version.okHttp}" }

    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Version.moshiKotlin}" }
    val moshi by lazy { "com.squareup.moshi:moshi:${Version.moshi}" }
    val converterMoshi by lazy { "com.squareup.retrofit2:converter-moshi:${Version.converterMoshi}" }
    val moshiKotlinCodegen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshiKotlinCodegen}" }

    val koin by lazy { "io.insert-koin:koin-android:${Version.koin}" }

    val coil by lazy { "io.coil-kt:coil:${Version.coil}" }

    val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModelKtx}" }

    val junit by lazy { "junit:junit:${Version.junit}" }
    val junitExt by lazy { "androidx.test.ext:junit:${Version.junitExt}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Version.espressoCore}" }
    val testRules by lazy { "androidx.test:rules:${Version.testRules}" }
    val coreTesting by lazy { "androidx.arch.core:core-testing:${Version.coreTesting}" }
    val mockk by lazy { "io.mockk:mockk:${Version.mockk}" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutinesTest}" }
}