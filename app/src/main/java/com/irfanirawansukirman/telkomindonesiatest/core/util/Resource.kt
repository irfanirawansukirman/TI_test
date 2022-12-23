package com.irfanirawansukirman.telkomindonesiatest.core.util

sealed class Resource<out T> {
    class Success<T>(val data: T?) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
}