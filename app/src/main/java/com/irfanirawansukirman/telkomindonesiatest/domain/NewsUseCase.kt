package com.irfanirawansukirman.telkomindonesiatest.domain

import com.irfanirawansukirman.telkomindonesiatest.core.util.Resource
import com.irfanirawansukirman.telkomindonesiatest.data.AppRepository
import com.irfanirawansukirman.telkomindonesiatest.data.AppRepositoryImpl
import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 22/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class NewsUseCase(private val appRepositoryImpl: AppRepositoryImpl): AppRepository {

    override suspend fun getTopStories(): Resource<Response<List<Int>>> {
        return appRepositoryImpl.getTopStories()
    }

    override suspend fun getNewsDetail(newsId: Int): Response<NewsDetail> {
        return appRepositoryImpl.getNewsDetail(newsId)
    }
}