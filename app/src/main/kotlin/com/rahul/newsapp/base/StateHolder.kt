package com.rahul.newsapp.base

import kotlinx.coroutines.flow.Flow

/**
 * Note: It's a new version of [StateHolder] which is used to smooth [StateHolder] refactoring.
 * The only difference with the original [StateHolder] is [initialState] field
 *
 * An Interface the every state holder must implement.
 *
 * @param P A class the encapsulates the data necessary to initialize the screen
 * @param T A type of ui state the state holder will expose
 */
abstract class StateHolder<P, T> {
    /**
     * Function to create state holder parameters, utilizing navigation arguments
     *
     * @return params
     */
    protected abstract val params: P

    /**
     * The flow the the state holder emits it's ui state through
     */
    abstract val state: Flow<T>

    /**
     * Initial state which is displayed while main content is loaded
     */
    abstract val initialState: T
}
