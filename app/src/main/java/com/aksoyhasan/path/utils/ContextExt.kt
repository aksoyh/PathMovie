package com.aksoyhasan.path.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aksoyhasan.path.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

fun Context.showCommonPopup(message: String) {
    MaterialAlertDialogBuilder(this).apply {
        setCancelable(false)
        setMessage(message)
        setNeutralButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        show()
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showDatePickerDialog(datePickerDialogListener: DatePickerDialog.OnDateSetListener) {

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        this, datePickerDialogListener,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.show()
}

fun Context.share(data: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, data)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.copyToClipBoard(data: String) {
    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("", data)
    clipboard.setPrimaryClip(clip)

    showToast("Kod Kopyalandı")
}

fun Context.showLoading() {
    when(this) {
        is Fragment -> {
            this.requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.requireActivity().findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.VISIBLE
        }
        is Activity -> {
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.VISIBLE
        }
    }
}

fun Context.hideLoading() {
    when(this) {
        is Fragment -> {
            this.requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.requireActivity().findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.GONE
        }
        is Activity -> {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.GONE
        }
    }
}