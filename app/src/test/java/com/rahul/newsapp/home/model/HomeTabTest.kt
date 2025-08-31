package com.rahul.newsapp.home.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class HomeTabTest {
    @Test
    fun `properties are stored`() {
        val tab = HomeTab(type = Type.TOP_HEADLINES, labelResId = 1, contentDescriptionResId = 2, iconResId = 3, testTag = "TAG")
        assertEquals(Type.TOP_HEADLINES, tab.type)
        assertEquals(1, tab.labelResId)
        assertEquals(2, tab.contentDescriptionResId)
        assertEquals(3, tab.iconResId)
        assertEquals("TAG", tab.testTag)
    }

    @Test
    fun `equality works`() {
        val t1 = HomeTab(Type.NEWS_SOURCE, 1, 2, 3, "BUS")
        val t2 = HomeTab(Type.NEWS_SOURCE, 1, 2, 3, "BUS")
        val t3 = HomeTab(Type.COUNTRY, 1, 2, 3, "SCI")
        assertEquals(t1, t2)
        assertNotEquals(t1, t3)
    }
}
