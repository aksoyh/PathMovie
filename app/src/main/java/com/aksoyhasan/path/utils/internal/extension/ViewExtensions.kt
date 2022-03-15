package com.aksoyhasan.path.utils.internal.extension

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ViewGroup?.inflate(@LayoutRes layoutId: Int, attachToParent: Boolean = true): T =
        DataBindingUtil.inflate(
                LayoutInflater.from(this!!.context),
                layoutId,
                this,
                attachToParent
        )

fun <T : ViewDataBinding> View?.inflate(@LayoutRes layoutId: Int, attachToParent: Boolean = true): T {
    return DataBindingUtil.inflate(
            LayoutInflater.from(this!!.context),
            layoutId,
            parent as? ViewGroup,
            attachToParent
    )
}

fun TextView.setPrimaryColor(color: Int) {
    this.setTextColor(color)
}

fun ImageView.setPrimaryColor(color: Int) {
    this.imageTintList = ColorStateList.valueOf(color)
}