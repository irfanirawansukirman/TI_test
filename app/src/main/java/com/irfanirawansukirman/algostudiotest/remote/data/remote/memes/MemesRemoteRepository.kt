package com.irfanirawansukirman.algostudiotest.remote.data.remote.memes

import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 19/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
interface MemesRemoteRepository {

    suspend fun getMemes(): Response<Memes>
}