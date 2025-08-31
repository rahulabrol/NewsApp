package com.rahul.newsapp.local.entity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class LocalSourceTest {
    @Test
    fun `properties stored and equality`() {
        val s1 = LocalSource(sourceId = "id", name = "Name")
        val s2 = LocalSource(sourceId = "id", name = "Name")
        val s3 = LocalSource(sourceId = "id2", name = "Name2")
        assertEquals("id", s1.sourceId)
        assertEquals("Name", s1.name)
        assertEquals(s1, s2)
        assertNotEquals(s1, s3)
    }
}
