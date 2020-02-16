package com.prudhvir3ddy.dailybugle.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Connection {
  companion object {
    fun hasNetwork(context: Context): Boolean {
      var isConnected = false // Initial Value
      val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
      if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
      return isConnected
    }
  }
}