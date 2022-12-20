package com.irfanirawansukirman.algostudiotest.presentation.memes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.algostudiotest.core.base.BaseViewModel
import com.irfanirawansukirman.algostudiotest.core.util.Resource
import com.irfanirawansukirman.algostudiotest.core.util.ViewState
import com.irfanirawansukirman.algostudiotest.domain.MemesUseCase
import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class MemesViewModel @Inject constructor(
    private val memesUseCase: MemesUseCase
) : BaseViewModel() {

    private val _memes = MutableLiveData<ViewState<Memes>>()
    val memes: LiveData<ViewState<Memes>>
        get() = _memes

    fun getMemes() {
        viewModelScope.launch {
            _memes.value = ViewState.Loading
            when (val response = memesUseCase.getMemes()) {
                is Resource.Success -> {
                    if (response.data?.isSuccessful == true) {
                        _memes.value = ViewState.Success(response.data.body())
                    } else {
                        _memes.value = ViewState.Error()
                    }

                    _memes.value = ViewState.Dismiss
                }
                is Resource.Error -> {
                    _memes.value = ViewState.Error(response.exception)
                    _memes.value = ViewState.Dismiss
                }
                else -> _memes.value = ViewState.Dismiss
            }
        }
    }
}