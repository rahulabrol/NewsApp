package com.rahul.newsapp.local

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset

class OffsetDateTimeConverterTest {
    @Test
    fun `fromOffsetDateTime returns null when input is null`() {
        val result = OffsetDateTimeConverter.fromOffsetDateTime(null)
        assertNull(result)
    }

    @Test
    fun `toOffsetDateTime returns null when input is null`() {
        val result = OffsetDateTimeConverter.toOffsetDateTime(null)
        assertNull(result)
    }

    @Test
    fun `converts OffsetDateTime to string and back`() {
        val dateTime = OffsetDateTime.of(2024, 9, 7, 12, 34, 56, 0, ZoneOffset.UTC)
        val str = OffsetDateTimeConverter.fromOffsetDateTime(dateTime)
        val back = OffsetDateTimeConverter.toOffsetDateTime(str)
        assertEquals(dateTime, back)
    }
}
