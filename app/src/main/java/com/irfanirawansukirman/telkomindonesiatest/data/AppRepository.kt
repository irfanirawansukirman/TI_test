package com.irfanirawansukirman.telkomindonesiatest.data

import com.irfanirawansukirman.telkomindonesiatest.core.util.Resource
import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
interface AppRepository {

    suspend fun getTopStories(): Resource<Response<List<Int>>>

    suspend fun getNewsDetail(newsId: Int): Response<NewsDetail>
}