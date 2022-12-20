package com.irfanirawansukirman.algostudiotest.data

import com.irfanirawansukirman.algostudiotest.core.util.Resource
import com.irfanirawansukirman.algostudiotest.remote.data.remote.memes.MemesRemoteRepositoryImpl
import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class AppRepositoryImpl @Inject constructor(
    private val memesRemoteRepositoryImpl: MemesRemoteRepositoryImpl
) : AppRepository {

    override suspend fun getMemes(): Resource<Response<Memes>> {
        return try {
            val response = memesRemoteRepositoryImpl.getMemes()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}