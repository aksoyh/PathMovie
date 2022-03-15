package com.aksoyhasan.path.utils.internal.extension

import android.content.res.Resources


val Int.Companion.ZERO: Int
    get() = 0

fun Int?.notZero(): Boolean {
    return this != 0
}

fun Int?.zeroIfNull() = this ?: 0

fun Int.pxToDp(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}
