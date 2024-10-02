package com.rahul.newsapp.web

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.LifecycleOwner
import com.rahul.newsapp.MainActivity

/**
 * Created by abrol at 06/09/24.
 */
interface CustomTabLauncher : LifecycleOwner {
    fun launchTab(uri: Uri) {
        val builder = CustomTabsIntent.Builder()
            .setShowTitle(true)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this as MainActivity, uri)
    }
}
