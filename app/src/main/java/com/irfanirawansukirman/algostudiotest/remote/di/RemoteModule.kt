package com.irfanirawansukirman.algostudiotest.remote.di

import android.app.Application
import com.irfanirawansukirman.algostudiotest.BuildConfig
import com.irfanirawansukirman.algostudiotest.remote.factory.ApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        application: Application,
    ): Retrofit =
        ApiFactory.build(
            application,
            BuildConfig.API_URL
        )
}