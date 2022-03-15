package com.aksoyhasan.path.utils.internal.extension

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.util.*

private const val HEX_CHARS = "0123456789ABCDEF"
private const val PATTERN = "[^?0-9]+"

fun String.trimAll() = this.replace("\\s".toRegex(), "")

fun String?.getInt(): Int {
    return this?.let {
        val replacedString: String? = this.replace(Regex(PATTERN), String.EMPTY)
        if (replacedString.isNullOrBlank()) 0 else replacedString.toInt()
    }.zeroIfNull()
}

fun String.toSpanned(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            this.replace("\n", "<br />"),
            Html.FROM_HTML_MODE_LEGACY
        )
    } else
        @Suppress("DEPRECATION")
        Html.fromHtml(this.replace("\n", "<br />"))
}

fun String?.emptyIfNull(): String {
    return if (this.isNullOrBlank()) String.EMPTY else this
}

val String.Companion.EMPTY: String
    get() = ""

fun String.appendPercentage(): String {
    return String.format("%s %s", this, "%".padStart(0))
}

fun String.appendMeterPerSecond(): String {
    return String.format("%s %s", this, "m/s".padStart(0))
}

fun String.appendCelsius(): String {
    return String.format("%s %s", this, "m/s".padStart(0))
}

fun String.toPlateArray(): MutableList<String> {
    val plateArray = mutableListOf<String>()
    var cityCodeLength = 2
    var cityCode = this.take(cityCodeLength)
    if (cityCode.filter { it.isDigit() }.length < 2) {
        plateArray.add("0${cityCode.take(cityCode.filter { it.isDigit() }.length)}")
        cityCodeLength = cityCode.filter { it.isDigit() }.length
    } else {
        plateArray.add(this.take(cityCodeLength))
    }
    val afterCityCode = this.takeLast(this.length - cityCodeLength)
    plateArray.add(afterCityCode.filter { !it.isDigit() })
    plateArray.add(afterCityCode.filter { it.isDigit() })
    return plateArray
}

fun String.toPlateWithSpaces(): String {
    return this.toPlateArray().joinToString(separator = " ")
}

fun String.toPlateCityCode(): String {
    return if (this.length == 1) {
        "0$this"
    } else {
        this
    }
}

fun String.convertRgbaToArgb(defaultColor: String): String {
    // check color have A
    return when {
        this.length == 6 -> this
        this.length > 6 -> {
            val color = this.substring(1, 7)
            val transparency = this.substring(7, this.lastIndex + 1)
            "#$transparency$color"
        }
        else -> {
            defaultColor
        }
    }
}

fun String?.controlColorNullProperty(defaultValue: String): String {
    return this?.convertRgbaToArgb(defaultValue) ?: defaultValue
}

fun String?.putItIfNotNull(defaultValue: String): String {
    return this ?: defaultValue
}

fun String.getAge(): String {
    return (Calendar.getInstance().get(Calendar.YEAR) - this.toInt()).toString()
}

fun String.getFullName(lastName: String): String {
    return "$this $lastName"
}

fun String.getFirstName(): String {
    return try {
        this.split(" ")[0]
    } catch (e: Exception) {
        return ""
    }
}

fun String.getLastName(): String {
    return try {
        this.split(" ")[1]
    } catch (e: Exception) {
        return ""
    }
}

fun String.getClearPhoneNumber(): String {
    return this.replace(" ", "")
}

fun String.getFormattedPhoneNumber(): String {
    val phone = this
    if (phone.length == 10) {
        val first = phone.substring(0, 3)
        val second = phone.substring(3, 6)
        val third = phone.substring(6, 8)
        val forth = phone.substring(8, 10)

        return "$first $second $third $forth"
    } else {
        return this
    }
}

