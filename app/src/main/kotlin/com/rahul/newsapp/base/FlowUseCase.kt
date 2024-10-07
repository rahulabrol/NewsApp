package com.rahul.newsapp.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

/**
 * An Base contract for use cases that return a result flow
 *
 * Can initiate suspend function calls, but can invoke without suspend
 *
 * Common usage is to call a repository suspend function (firing an initial event upstream), or
 * calling a repository function that returns a flow directly
 *
 * This was inspired by the SubjectInteractor class in Tivi project
 *
 * @param P Parameters: encapsulates the data necessary to run the use case, can be Unit
 * @param R Result: A result type the use case will return
 */
@OptIn(ExperimentalCoroutinesApi::class)
abstract class FlowUseCase<P : Any, T> {
    // Ideally this would be buffer = 0, since we use flatMapLatest below, BUT invoke is not
    // suspending. This means that we can't suspend while flatMapLatest cancels any
    // existing flows. The buffer of 1 means that we can use tryEmit() and buffer the value
    // instead, resulting in mostly the same result.
    private val paramState: MutableSharedFlow<P> = MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val flow: Flow<T> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createObservable(it) }
        .distinctUntilChanged()

    /**
     * Primary entry point into the Use Case, invoke it with expected parameters, and receive a flow
     * providing the expected data type
     *
     * This flow should immediately emit!
     * This will ensure that this flow is safe to map, transform, or combine in our structured
     * concurrency model
     *
     * @param params
     * @return flow of typed data
     */
    operator fun invoke(params: P): Flow<T> {
        paramState.tryEmit(params)
        return flow
    }

    /**
     * This function is implemented by each FlowUseCase, provides the data flow, utilizing required
     * parameters
     *
     * This flow should immediately emit!
     * This will ensure that this flow is safe to map, transform, or combine in our structured
     * concurrency model
     *
     * @param params
     * @return flow of typed data
     */
    protected abstract fun createObservable(params: P): Flow<T>
}
