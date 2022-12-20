package com.irfanirawansukirman.algostudiotest.data

import com.irfanirawansukirman.algostudiotest.core.util.Resource
import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by Irfan Irawan Sukirman on 20/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */
interface AppRepository {

    suspend fun getMemes(): Resource<Response<Memes>>
}