package com.example.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by karanjeet on 22/10/19
 */
class Network {

    companion object{
        fun isNetworkAvailable(context : Context):Boolean{
            val connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo : NetworkInfo? = null
            if(networkInfo == null)
                networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo!=null && networkInfo.isConnected
        }
    }

}