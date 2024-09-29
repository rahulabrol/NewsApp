package com.rahul.newsapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlin.experimental.ExperimentalTypeInference

/**
 * Allows running an operation within a flow, allows running operations when collecting on combined flows
 *
 * Can be run like launching a new coroutine from a coroutine scope, bound to the coroutine scope of the
 * flow collector
 *
 * @param block
 * @return flow to run operation
 */
@OptIn(ExperimentalTypeInference::class)
fun launchFlow(@BuilderInference block: suspend FlowCollector<Unit>.() -> Unit): Flow<Unit> = flow {
    emit(Unit)
    block()
}