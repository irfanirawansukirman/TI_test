package com.irfanirawansukirman.algostudiotest.remote.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by Irfan Irawan Sukirman on 19/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

@JsonClass(generateAdapter = true)
data class Memes(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "success")
    val success: Boolean?
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "memes")
    val memes: List<Meme>?
)

@JsonClass(generateAdapter = true)
data class Meme(
    @Json(name = "box_count")
    val boxCount: Int?,
    @Json(name = "captions")
    val captions: Int?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int?
)