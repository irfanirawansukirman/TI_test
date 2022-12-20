package com.irfanirawansukirman.algostudiotest.di

import com.irfanirawansukirman.algostudiotest.core.base.BaseModule
import com.irfanirawansukirman.algostudiotest.data.AppRepositoryImpl
import com.irfanirawansukirman.algostudiotest.domain.MemesUseCase
import com.irfanirawansukirman.algostudiotest.remote.data.remote.memes.MemesRemoteRepositoryImpl
import com.irfanirawansukirman.algostudiotest.remote.data.service.MemesService
import com.irfanirawansukirman.algostudiotest.remote.di.RemoteModule
import com.irfanirawansukirman.algostudiotest.remote.factory.ApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
@Module(includes = [BaseModule::class, RemoteModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideMemesService(retrofit: Retrofit): MemesService = ApiFactory.getService(retrofit)

    @Singleton
    @Provides
    fun provideMemesRemoteRepositoryImpl(memesService: MemesService): MemesRemoteRepositoryImpl {
        return MemesRemoteRepositoryImpl(memesService)
    }

    @Singleton
    @Provides
    fun provideAppRepositoryImpl(memesRemoteRepositoryImpl: MemesRemoteRepositoryImpl): AppRepositoryImpl {
        return AppRepositoryImpl(memesRemoteRepositoryImpl)
    }

    @Singleton
    @Provides
    fun provideMemesUseCase(appRepositoryImpl: AppRepositoryImpl): MemesUseCase {
        return MemesUseCase(appRepositoryImpl)
    }
}