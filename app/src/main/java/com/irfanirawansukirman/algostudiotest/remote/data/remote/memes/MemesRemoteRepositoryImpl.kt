package com.irfanirawansukirman.algostudiotest.remote.data.remote.memes

import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import com.irfanirawansukirman.algostudiotest.remote.data.service.MemesService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Irfan Irawan Sukirman on 19/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class MemesRemoteRepositoryImpl @Inject constructor(private val memesService: MemesService): MemesRemoteRepository {

    override suspend fun getMemes(): Response<Memes> {
        return memesService.getMemes()
    }
}