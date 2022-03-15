package com.aksoyhasan.path

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.aksoyhasan.path.utils.InternetConnectionUtil
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class MovieApp : Application() {

    private lateinit var mContext: Context
    lateinit var appContext: Context


    fun getContext(): Context {
        mContext = this@MovieApp
        return this@MovieApp
    }

    override fun onCreate() {
        super.onCreate()
        getContext()
        mContext = this@MovieApp
        Paper.init(mContext)
        InternetConnectionUtil.init(this@MovieApp)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

}