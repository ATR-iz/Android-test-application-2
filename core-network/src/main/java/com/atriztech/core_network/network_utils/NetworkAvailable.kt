package com.atriztech.core_network.network_utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkAvailable {
    companion object{
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}