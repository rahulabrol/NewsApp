package com.rahul.newsapp.headlines.stateholder

import androidx.compose.material3.SnackbarDuration
import com.rahul.newsapp.R
import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.common.snackbar.SnackBarUiState
import com.rahul.newsapp.utils.connectivity.Network
import com.rahul.newsapp.utils.connectivity.NetworkConnectivity
import com.rahul.newsapp.utils.launchFlow
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * This class is used to check if the internet connection is available or not.
 *
 * Created by abrol at 29/09/24.
 */
@ViewModelScoped
class NetworkConnectivityStateHolder @Inject constructor(
    private val networkConnectivity: NetworkConnectivity
) : StateHolder<Unit, NetworkConnectivityStateHolder.UiState>() {

    override val params = Unit
    override val initialState: UiState = UiState()
    private val _state = MutableStateFlow(InternalState())

    override val state: Flow<UiState> = combine(_state, networkState()) { internal, _ ->
        UiState(
            errorSnackBar = SnackBarUiState(
                message = R.string.no_network,
                actionLabel = R.string.retry,
                duration = SnackbarDuration.Indefinite
            ).takeIf {
                internal.showSnackBar
            },
            connectedState = internal.showSnackBar.not()
        )
    }

    private fun networkState() = launchFlow {
        networkConnectivity.state.collect { networkState ->
            if (networkState is Network.State.Disconnected && !_state.value.snackBarVisible) {
                _state.update {
                    it.copy(
                        snackBarVisible = true,
                        showSnackBar = true
                    )
                }
            } else if (networkState is Network.State.Connected) {
                _state.update {
                    it.copy(
                        snackBarVisible = false,
                        showSnackBar = false
                    )
                }
            }
        }
    }

    /**
     * On retry click
     *
     */
    internal suspend fun onRetryClick() {
        _state.update {
            it.copy(
                snackBarVisible = false,
                showSnackBar = false
            )
        }
        networkState().collect {}
    }

    /**
     * Internal Network Connectivity State
     *
     * @property showSnackBar
     * @property snackBarVisible
     */
    /**
     * Internal Network Connectivity State
     *
     * @property showSnackBar
     * @property snackBarVisible
     */
    data class InternalState(
        val showSnackBar: Boolean = false,
        val snackBarVisible: Boolean = false
    )

    /**
     * Ui state
     *
     * @property connectedState
     * @property errorSnackBar
     * @constructor Create empty Ui state
     */
    data class UiState(
        val errorSnackBar: SnackBarUiState? = null,
        val connectedState: Boolean = true,
    )
}
