package com.irfanirawansukirman.algostudiotest.remote.data.service

import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Irfan Irawan Sukirman on 19/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
interface MemesService {

    @GET("get_memes")
    suspend fun getMemes(): Response<Memes>
}