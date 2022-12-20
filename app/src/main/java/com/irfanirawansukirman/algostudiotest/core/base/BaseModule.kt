package com.irfanirawansukirman.algostudiotest.core.base

import com.irfanirawansukirman.algostudiotest.core.util.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider(): CoroutinesContextProvider = CoroutinesContextProvider()
}