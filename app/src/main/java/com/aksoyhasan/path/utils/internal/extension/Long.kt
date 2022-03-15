package com.aksoyhasan.path.utils.internal.extension

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


const val SECOND_IN_MILLIS = 1000
const val MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 1000
const val HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 1000

val Long.Companion.ZERO: Long
    get() = 0

fun Long?.zeroIfNull() = this ?: 0

fun Long.toRemainingTime(prefix: String = ""): String? {
    return prefix + DateUtils.formatElapsedTime(this)
}

fun Long.millisToSecond(): Long {
    return this.div(1000L)
}

fun Long.isPositive() = this > 0

fun Long?.toDate(): Date {
    if (this == null)
        return Calendar.getInstance().time
    return Date(this)
}