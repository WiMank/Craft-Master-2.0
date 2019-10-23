package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class NetManager(private var context: Context) {
    fun isConnectedToNetwork(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var isConnected = false
        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return isConnected
    }
}
