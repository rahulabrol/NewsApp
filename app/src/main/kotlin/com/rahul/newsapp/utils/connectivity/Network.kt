package com.rahul.newsapp.utils.connectivity

/**
 * Network interface
 *
 * Created by abrol at 29/09/24.
 */
sealed interface Network {

    /**
     * Network state class
     */
    sealed interface State {
        /**
         * Connected
         */
        data object Connected : State

        /**
         * Disconnected
         */
        data object Disconnected : State
    }
}
