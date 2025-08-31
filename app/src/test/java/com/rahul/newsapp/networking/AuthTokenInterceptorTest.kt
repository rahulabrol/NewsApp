package com.rahul.newsapp.networking

import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthTokenInterceptorTest {
    @MockK
    lateinit var chain: Interceptor.Chain

    private lateinit var interceptor: AuthTokenInterceptor

    private val apiKey = "test-api-key"

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        interceptor = AuthTokenInterceptor(apiKey)
    }

    @Test
    fun `adds X-Api-Key header and proceeds`() {
        val request = Request.Builder().url("http://example.com").build()
        val response =
            Response
                .Builder()
                .code(200)
                .protocol(Protocol.HTTP_1_1)
                .message("OK")
                .request(request)
                .build()

        every { chain.request() } returns request
        every { chain.proceed(any()) } returns response

        val result = interceptor.intercept(chain)
        assertEquals(200, result.code)

        val captured: CapturingSlot<Request> = slot()
        verify { chain.proceed(capture(captured)) }
        assertEquals(apiKey, captured.captured.header("X-Api-Key"))
    }
}
