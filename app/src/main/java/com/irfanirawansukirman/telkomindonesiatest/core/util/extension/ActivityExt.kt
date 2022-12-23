package com.irfanirawansukirman.telkomindonesiatest.core.util.extension

import android.app.Activity
import android.widget.Toast

/**
 * Created by Irfan Irawan Sukirman on 23/12/22
 * Copyright (c) 2022 PT. Sampingan Mitra Indonesia, All Rights Reserved.
 */

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}