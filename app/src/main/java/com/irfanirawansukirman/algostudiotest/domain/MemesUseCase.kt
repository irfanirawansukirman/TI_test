package com.irfanirawansukirman.algostudiotest.domain

import com.irfanirawansukirman.algostudiotest.core.util.Resource
import com.irfanirawansukirman.algostudiotest.data.AppRepository
import com.irfanirawansukirman.algostudiotest.data.AppRepositoryImpl
import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
class MemesUseCase @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) : AppRepository {

    override suspend fun getMemes(): Resource<Response<Memes>> {
        return appRepositoryImpl.getMemes()
    }
}