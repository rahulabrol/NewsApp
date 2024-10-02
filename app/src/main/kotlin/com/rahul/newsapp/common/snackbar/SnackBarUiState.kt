package com.rahul.newsapp.common.snackbar

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration

/**
 * Ui State to model SnackBars in the application
 *
 * @property message
 * @property actionLabel
 * @property actionId
 * @property duration
 */
data class SnackBarUiState(
    @StringRes val message: Int,
    @StringRes val actionLabel: Int,
    val actionId: String? = null,
    val duration: SnackbarDuration = SnackbarDuration.Indefinite
)
