package com.rahul.newsapp.base

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

private class IntDoublerUseCase : ResultUseCase<Int, Int>() {
    override suspend fun doWork(params: Int): Int = params * 2
}

class ResultUseCaseTest {
    @Test
    fun `invoke emits one value then completes`() =
        runTest {
            val usecase = IntDoublerUseCase()
            val result = usecase(21).first()
            assertEquals(42, result)
        }
}
