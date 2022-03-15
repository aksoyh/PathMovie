package com.aksoyhasan.path.utils.internal.extension

import android.app.DatePickerDialog
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.util.*


internal fun <T : Parcelable> Fragment.getParcelable(key: String): T? {
    return this.arguments?.getParcelable<T>(key)
}

internal fun <T : Parcelable> Fragment.getParcelableList(key: String): MutableList<T>? {
    return arguments?.getParcelableArrayList<T>(key) as MutableList<T>
}

fun Fragment.openDatePicker(minDate: Long? = null, dateTime: (day: Int, month: Int, year: Int) -> Unit) {
    val dialog = DatePickerDialog(this.requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        dateTime.invoke(dayOfMonth, month + 1, year)
    }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    if (minDate != null) {
        dialog.datePicker.minDate = minDate
    }
    dialog.show()
}