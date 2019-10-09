package com.wimank.craftmaster.tz.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


@Suppress("DEPRECATION")
class NetManager(private var context: Context) {
    fun isInternetOn(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            //TODO: Проверить с WI-FI
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            val network = connectivityManager.activeNetworkInfo ?: return false
            network.type == ConnectivityManager.TYPE_WIFI
        }
    }
}
