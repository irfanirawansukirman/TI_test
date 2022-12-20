package com.irfanirawansukirman.algostudiotest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irfanirawansukirman.algostudiotest.core.util.ViewModelFactory
import com.irfanirawansukirman.algostudiotest.core.util.ViewModelKey
import com.irfanirawansukirman.algostudiotest.presentation.memes.MemesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
@ExperimentalCoroutinesApi
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MemesViewModel::class)
    internal abstract fun bindMemesViewModel(viewModel: MemesViewModel): ViewModel
}