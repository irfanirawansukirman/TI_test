package com.irfanirawansukirman.algostudiotest.core.util

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    object Dismiss : ViewState<Nothing>()
    data class Success<T>(val data: T?) : ViewState<T>()
    data class Error(val exception: Exception? = null) : ViewState<Nothing>()
}