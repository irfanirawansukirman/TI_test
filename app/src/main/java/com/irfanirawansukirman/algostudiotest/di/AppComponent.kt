package com.irfanirawansukirman.algostudiotest.di

import android.app.Application
import com.irfanirawansukirman.algostudiotest.presentation.memes.MemesActivity
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [ViewModelModule::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MemesActivity)
}