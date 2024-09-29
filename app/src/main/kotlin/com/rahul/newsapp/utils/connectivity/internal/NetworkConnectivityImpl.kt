package com.rahul.newsapp.utils.connectivity.internal

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.rahul.newsapp.utils.connectivity.Network
import com.rahul.newsapp.utils.connectivity.NetworkConnectivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Implementation of NetworkConnectivity
 *
 * @param context
 * Created by abrol at 29/09/24.
 */
internal class NetworkConnectivityImpl(@ApplicationContext context: Context) : NetworkConnectivity {

    override val state: Flow<Network.State> = callbackFlow {

        val connectivityManager = ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        ) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                trySend(Network.State.Connected)
            }

            override fun onLost(network: android.net.Network) {
                trySend(Network.State.Disconnected)
            }

            override fun onUnavailable() {
                trySend(Network.State.Disconnected)
            }
        }
        trySend(Network.State.Disconnected)
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

}