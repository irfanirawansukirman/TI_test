package com.irfanirawansukirman.telkomindonesiatest.presentation.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.irfanirawansukirman.telkomindonesiatest.core.util.CoroutinesContextProvider
import com.irfanirawansukirman.telkomindonesiatest.core.util.Resource
import com.irfanirawansukirman.telkomindonesiatest.core.util.ViewState
import com.irfanirawansukirman.telkomindonesiatest.domain.NewsUseCase
import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail
import com.irfanirawansukirman.telkomindonesiatest.util.MainCoroutinesRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 23/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @RelaxedMockK
    lateinit var newsObserver: Observer<ViewState<List<NewsUI>>>

    @RelaxedMockK
    lateinit var newsUseCase: NewsUseCase

    @RelaxedMockK
    lateinit var mockException: Exception

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsViewModel
    private lateinit var coroutinesContextProvider: CoroutinesContextProvider

    private val newsDetail = NewsDetail(
        "Udin",
        123,
        123,
        listOf(123),
        123,
        123123123,
        "Udin The Explorer",
        "",
        ""
    )

    private val dummyId = 123123
    private val newsId = dummyId

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this)

        coroutinesContextProvider = CoroutinesContextProvider()
        viewModel = NewsViewModel(newsUseCase, coroutinesContextProvider)
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }

    @Test
    fun `get top stories with success`() = coroutinesRule.runBlockingTest {
        val newsUI = NewsUI(
            "Udin The Explorer",
            "Udin",
            "02/Jan/1970, 17:12"
        )

        coEvery { newsUseCase.getTopStories() } returns Resource.Success(
            Response.success(
                listOf(
                    dummyId
                )
            )
        )
        coEvery { newsUseCase.getNewsDetail(newsId) } returns Response.success(newsDetail)

        viewModel.news.observeForever(newsObserver)
        viewModel.getTopStories()
        viewModel.getNewsDetail(newsId)

        verifyOrder { newsObserver.onChanged(ViewState.Success(listOf(newsUI))) }
    }

    @Test
    fun `get top stories with failed`() = coroutinesRule.runBlockingTest {
        coEvery { newsUseCase.getTopStories() } returns Resource.Error(mockException)
        coEvery { newsUseCase.getNewsDetail(newsId) } returns Response.success(newsDetail)

        viewModel.news.observeForever(newsObserver)
        viewModel.getTopStories()
        viewModel.getNewsDetail(newsId)

        verifyOrder { newsObserver.onChanged(ViewState.Error(mockException)) }
    }
}
