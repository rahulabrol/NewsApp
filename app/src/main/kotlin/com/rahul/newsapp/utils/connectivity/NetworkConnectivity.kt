package com.rahul.newsapp.utils.connectivity

import kotlinx.coroutines.flow.Flow

/**
 * Network Connectivity
 *
 * Created by abrol at 29/09/24.
 */
interface NetworkConnectivity {

    /**
     * Network state presented as a flow for easy consumption
     * of required knowledge of the network state.
     */
    val state: Flow<Network.State>
}