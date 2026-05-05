package com.vutheanh.pettranslate.core.extensions

import android.content.Context

fun String.toDrawableRes(context: Context): Int {
    return context.resources.getIdentifier(this, "drawable", context.packageName)
}
