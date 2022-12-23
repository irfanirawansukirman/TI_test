package com.irfanirawansukirman.telkomindonesiatest.core.util.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Irfan Irawan Sukirman on 23/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

fun Long.toLocaleTime(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy, HH:mm", Locale.getDefault())
    return simpleDateFormat.format(Date(this))
}