package com.aksoyhasan.path.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.aksoyhasan.path.R
import kotlinx.android.synthetic.main.dialog_something_went_wrong.*
import java.math.BigInteger
import java.security.MessageDigest

class UiHelper {
    companion object{
        fun customErrorDialog(activity: Activity, error: String):Dialog?{
            if (error != null && !activity.isFinishing) {
                val dialog = Dialog(activity)
                dialog.setContentView(R.layout.dialog_something_went_wrong)
                dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.show()

                val errorOk = dialog.error_ok
                val errorMessage = dialog.error_message

                var err = error

                val boolean1 = error.contains("connect")
                val boolean2 = error.contains("fail")
                val boolean3 = error.contains("port")
                val boolean4 = error.contains("time")
                val boolean5 = error.contains("ms")
                val boolean6 = error.contains("unreachable")

                if (boolean4 || boolean5) {
                    err =  "Cannot response from the server"
                }else if (boolean1 || boolean2 || boolean3 || boolean6) {
                    err = "Please check your internet connection."
                }

                errorMessage.text = err
                errorOk.setOnClickListener {
                    if (activity.toString().contains("SplashActivity")) {
                        activity.finish()
                    } else {
                        dialog.dismiss()
                    }
                }
                return dialog
            }
            return null
        }

        fun hasInternetConnection(activity: Activity): Boolean {
            val connectivityManager = activity.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
            return false
        }

        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun getTimeStamp(): String {
            val tsLong = System.currentTimeMillis() / 1000
            return tsLong.toString()
        }
    }
}