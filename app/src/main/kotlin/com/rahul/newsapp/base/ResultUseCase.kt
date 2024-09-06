package com.rahul.newsapp.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * An Base contract for use cases that return a single result
 *
 * Can initiate suspend function calls, but can invoke without suspending
 *
 * This was inspired by the ResultInteractor class in Tivi project
 *
 * @param P Parameters: encapsulates the data necessary to run the use case, can be Unit
 * @param R Result: A result type the use case will return
 */
abstract class ResultUseCase<P, R> {
    /**
     * @param params A class that holds the necessary data to execute the use case
     * @return A result that emits type of [R]
     */
    operator fun invoke(params: P): Flow<R> = flow {
        emit(doWork(params))
    }

    /**
     * UseCase specific work should be implemented here
     *
     * @param params A class that holds the necessary data to execute the use case
     * @return result type of [R]
     */
    protected abstract suspend fun doWork(params: P): R
}
