 

package com.whatstoday.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun generateMD5HashFromText() {
        val originalText = "Expected string"
        val expectedMD5Hash = "c6c898bb3177a5cdab4183ea390c67ca"
        assertEquals(expectedMD5Hash, originalText.toMD5())
    }

    @Test
    fun generateMD5HashFromSpecialCharactersText() {
        val originalText = "^/4(0!§±§.,=+-'~`"
        val expectedMD5Hash = "893057cb57f94bc408b7fbf12fc72a19"
        assertEquals(expectedMD5Hash, originalText.toMD5())
    }

    @Test
    fun generateMD5HashFromEmptyText() {
        val originalText = ""
        val expectedHexadecimal = "d41d8cd98f00b204e9800998ecf8427e"
        assertEquals(expectedHexadecimal, originalText.toMD5())
    }

    @Test
    fun generateMD5HashFromUnexpectedText() {
        val originalText = "Not expected string"
        val expectedHexadecimal = "457870656374656420737472696e67"
        assertNotEquals(expectedHexadecimal, originalText.toMD5())
    }
}
