package com.aksoyhasan.path.utils.internal.extension

import java.text.NumberFormat
import java.util.*

val Double.Companion.ZERO: Int
    get() = 0

fun Double?.notZero(): Boolean {
    return this != 0.0
}

fun Double?.zeroIfNull() = this ?: 0.0

fun Double?.getPriceString(): String {

    return if (this == null)
        "0.00"
    else {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        numberFormat.currency = Currency.getInstance(Locale.US)
        val price = numberFormat.format(this)
        "$price"
    }
}