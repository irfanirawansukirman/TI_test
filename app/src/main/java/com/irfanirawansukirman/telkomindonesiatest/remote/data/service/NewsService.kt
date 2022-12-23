package com.irfanirawansukirman.telkomindonesiatest.remote.data.service

import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Irfan Irawan Sukirman on 22/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
interface NewsService {

    @GET("topstories.json?print=pretty")
    suspend fun getTopStories(): Response<List<Int>>

    @GET("item/{news_id}.json?print=pretty")
    suspend fun getNewsDetail(@Path("news_id") newsId: Int): Response<NewsDetail>
}