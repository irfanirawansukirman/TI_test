package com.irfanirawansukirman.telkomindonesiatest.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.telkomindonesiatest.core.util.CoroutinesContextProvider
import com.irfanirawansukirman.telkomindonesiatest.core.util.Resource
import com.irfanirawansukirman.telkomindonesiatest.core.util.ViewState
import com.irfanirawansukirman.telkomindonesiatest.domain.NewsUseCase
import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 22/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class NewsViewModel(
    private val newsUseCase: NewsUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : ViewModel() {

    private val _loader = MutableLiveData<ViewState<Nothing>>()
    val loader: LiveData<ViewState<Nothing>>
        get() = _loader

    private val _news = MutableLiveData<ViewState<List<NewsUI>>>()
    val news: LiveData<ViewState<List<NewsUI>>>
        get() = _news

    fun getTopStories() {
        viewModelScope.launch {
            _loader.value = ViewState.Loading
            when (val response = newsUseCase.getTopStories()) {
                is Resource.Success -> {
                    val isSuccessful = response.data?.isSuccessful ?: false
                    if (isSuccessful) {
                        val newsUI = mutableListOf<NewsUI>()
                        val body = response.data?.body() ?: emptyList()
                        try {
                            val data = body.map {
                                async(coroutinesContextProvider.io) { getNewsDetail(it) }
                            }.awaitAll()

                            withContext(coroutinesContextProvider.main) {
                                newsUI.clear()
                                data.forEach {
                                    val isSuccessfulForDetail = it.isSuccessful
                                    if (isSuccessfulForDetail) {
                                        it.body()?.toNewsUI()?.let { item -> newsUI.add(item) }
                                        _news.value = ViewState.Success(newsUI)
                                    } else {
                                        return@forEach
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            return@launch
                        }
                    }
                    _loader.value = ViewState.Dismiss
                }
                is Resource.Error -> {
                    _loader.value = ViewState.Dismiss
                    _news.value = ViewState.Error(response.exception)
                }
                else -> _loader.value = ViewState.Dismiss
            }
        }
    }

    suspend fun getNewsDetail(newsId: Int): Response<NewsDetail> {
        return newsUseCase.getNewsDetail(newsId)
    }
}