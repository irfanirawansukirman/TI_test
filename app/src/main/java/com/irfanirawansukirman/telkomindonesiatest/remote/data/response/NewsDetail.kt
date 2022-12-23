package com.irfanirawansukirman.telkomindonesiatest.remote.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Irfan Irawan Sukirman on 22/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

@JsonClass(generateAdapter = true)
data class NewsDetail(
    @Json(name = "by")
    val `by`: String?,
    @Json(name = "descendants")
    val descendants: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "kids")
    val kids: List<Int>?,
    @Json(name = "score")
    val score: Int?,
    @Json(name = "time")
    val time: Long?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)