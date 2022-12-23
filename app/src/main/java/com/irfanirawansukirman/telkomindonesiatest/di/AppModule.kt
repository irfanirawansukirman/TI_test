package com.irfanirawansukirman.telkomindonesiatest.di

import com.irfanirawansukirman.telkomindonesiatest.BuildConfig
import com.irfanirawansukirman.telkomindonesiatest.core.util.CoroutinesContextProvider
import com.irfanirawansukirman.telkomindonesiatest.data.AppRepositoryImpl
import com.irfanirawansukirman.telkomindonesiatest.domain.NewsUseCase
import com.irfanirawansukirman.telkomindonesiatest.presentation.news.NewsViewModel
import com.irfanirawansukirman.telkomindonesiatest.remote.data.remote.news.NewsRemoteRepositoryImpl
import com.irfanirawansukirman.telkomindonesiatest.remote.data.service.NewsService
import com.irfanirawansukirman.telkomindonesiatest.remote.factory.ApiFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

val appModule = module {
    single { CoroutinesContextProvider() }

    single { ApiFactory.build(BuildConfig.API_URL) }

    single { ApiFactory.getService<NewsService>(get()) }

    single { NewsRemoteRepositoryImpl(get()) }

    single { AppRepositoryImpl(get()) }

    single { NewsUseCase(get()) }

    viewModel { NewsViewModel(get(), get()) }
}