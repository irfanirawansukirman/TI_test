package com.irfanirawansukirman.telkomindonesiatest.presentation.news

import com.irfanirawansukirman.telkomindonesiatest.core.util.extension.toLocaleTime
import com.irfanirawansukirman.telkomindonesiatest.remote.data.response.NewsDetail

/**
 * Created by Irfan Irawan Sukirman on 23/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

data class NewsUI(
    val title: String, val by: String, val time: String
)

fun NewsDetail.toNewsUI(): NewsUI {
    return NewsUI(this.title.orEmpty(), this.by.orEmpty(), this.time?.toLocaleTime().orEmpty())
}