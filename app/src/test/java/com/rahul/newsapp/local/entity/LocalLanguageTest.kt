package com.rahul.newsapp.local.entity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class LocalLanguageTest {
    @Test
    fun `properties stored and equality`() {
        val l1 = LocalLanguage(id = "en", name = "English")
        val l2 = LocalLanguage(id = "en", name = "English")
        val l3 = LocalLanguage(id = "de", name = "German")
        assertEquals("en", l1.id)
        assertEquals("English", l1.name)
        assertEquals(l1, l2)
        assertNotEquals(l1, l3)
    }
}
