package com.irfanirawansukirman.algostudiotest

import android.app.Application
import com.irfanirawansukirman.algostudiotest.di.AppComponent
import com.irfanirawansukirman.algostudiotest.di.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainApplication : Application() {

    fun getAppComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .app(this)
            .build()
    }
}