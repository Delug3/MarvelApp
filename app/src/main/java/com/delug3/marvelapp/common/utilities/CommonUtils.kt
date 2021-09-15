package com.delug3.marvelapp.common.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class CommonUtils {
    companion object {
       fun isInternetAvailable(context: Context): Boolean {
           val result: Boolean
           val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            return result
        }
    }

}