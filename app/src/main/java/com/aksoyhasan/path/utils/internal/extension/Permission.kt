package com.aksoyhasan.path.utils.internal.extension

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val GRANTED = PackageManager.PERMISSION_GRANTED
private val DENIED = PackageManager.PERMISSION_DENIED
private val BLOCKED_OR_NEVER_ASKED = 2

fun List<String>.isNeverAskAgainSelectedPermissions(activity: Activity): Boolean {
    return this.any { it.isNeverAskAgainSelectedPermission(activity) }
}

fun String.isNeverAskAgainSelectedPermission(activity: Activity): Boolean {
    return this.getPermissionStatus(activity) == BLOCKED_OR_NEVER_ASKED
}

fun String.getPermissionStatus(activity: Activity): Int {
    if (ContextCompat.checkSelfPermission(
                    activity,
                    this) != PackageManager.PERMISSION_GRANTED
    ) {
        return if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, this)) {
            BLOCKED_OR_NEVER_ASKED
        } else DENIED
    }

    return GRANTED
}