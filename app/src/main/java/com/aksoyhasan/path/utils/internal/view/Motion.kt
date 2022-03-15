package com.aksoyhasan.path.utils.internal.view

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListenerAdapter

/**
 * @param show true, if the view will fade-in or false to fade-out
 */
fun View.fade(show: Boolean, isGone: Boolean? = false) {

    // Cancel any on-going animation
    ViewCompat.animate(this).cancel()

    if (show) {
        if (!isShown) {
            visibility = View.VISIBLE
            alpha = 0f
            ViewCompat.animate(this).alpha(1f).start()
        }
    } else {
        if (isShown) {
            alpha = 1f
            ViewCompat.animate(this)
                    .alpha(0f)
                    .setListener(object : ViewPropertyAnimatorListenerAdapter() {
                        override fun onAnimationEnd(view: View?) {
                            view?.let { ViewCompat.animate(it).setListener(null) }
                            view?.visibility = if (isGone == true) View.GONE else View.INVISIBLE
                        }
                    })
                    .start()
        }
    }
}
