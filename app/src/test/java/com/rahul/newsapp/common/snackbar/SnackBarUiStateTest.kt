package com.rahul.newsapp.common.snackbar

import androidx.compose.material3.SnackbarDuration
import org.junit.Assert.assertEquals
import org.junit.Test

class SnackBarUiStateTest {
    @Test
    fun `defaults are set correctly`() {
        val state = SnackBarUiState(message = 1, actionLabel = 2)
        assertEquals(1, state.message)
        assertEquals(2, state.actionLabel)
        assertEquals(null, state.actionId)
        assertEquals(SnackbarDuration.Indefinite, state.duration)
    }

    @Test
    fun `copy changes fields`() {
        val state = SnackBarUiState(message = 1, actionLabel = 2)
        val updated = state.copy(actionId = "retry", duration = SnackbarDuration.Short)
        assertEquals("retry", updated.actionId)
        assertEquals(SnackbarDuration.Short, updated.duration)
    }
}
