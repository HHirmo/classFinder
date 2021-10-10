import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PatternTest {

    @Test
    fun parsePattern_caseSensitive() {
        val pattern = parsePattern("FooBarBaz")
        assertEquals("Foo", pattern.words[0])
        assertEquals("Bar", pattern.words[1])
        assertEquals("Baz", pattern.words[2])
        assertFalse { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }

    @Test
    fun parsePattern_caseInsensitive() {
        val pattern = parsePattern("bar")
        assertEquals("bar", pattern.words[0])
        assertFalse { pattern.endsWithSpace }
        assertTrue { pattern.ignoreCase }
    }

    @Test
    fun parsePattern_startingWithLowerCase() {
        val pattern = parsePattern("ooBar")
        assertEquals("oo", pattern.words[0])
        assertEquals("Bar", pattern.words[1])
        assertFalse { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }

    @Test
    fun parsePattern_endsWithSpace() {
        val pattern = parsePattern("FooBar ")
        assertEquals("Foo", pattern.words[0])
        assertEquals("Bar", pattern.words[1])
        assertTrue { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }
}