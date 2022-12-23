package com.irfanirawansukirman.telkomindonesiatest.remote.data.remote.news

import com.irfanirawansukirman.telkomindonesiatest.core.util.Resource
import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail
import com.irfanirawansukirman.telkomindonesiatest.remote.data.service.NewsService
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 22/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class NewsRemoteRepositoryImpl(private val newsService: NewsService) : NewsRemoteRepository {

    override suspend fun getTopStories(): Resource<Response<List<Int>>> {
        return try {
            val response = newsService.getTopStories()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getNewsDetail(newsId: Int): Response<NewsDetail> {
        return newsService.getNewsDetail(newsId)
    }
}