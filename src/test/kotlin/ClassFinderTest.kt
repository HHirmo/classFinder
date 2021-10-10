import org.junit.Test
import kotlin.test.*

class ClassFinderTest {

    private val classFinder = ClassFinder()

    @Test
    fun sortClasses() {
        assertEquals(
            mutableListOf("alphaClass", "sigmaClass"),
            classFinder.sortClasses(mutableListOf("sigmaClass", "alphaClass"))
        )
    }

    @Test
    fun sortClasses_ignorePackageName() {
        assertEquals(
            mutableListOf("z.z.alphaClass", "a.sigmaClass"),
            classFinder.sortClasses(mutableListOf("a.sigmaClass", "z.z.alphaClass"))
        )
    }

    @Test
    fun patternMatch_searchWordsByFirstLetter() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("FB")) }
    }

    @Test
    fun patternMatch_ignorePackageName() {
        assertFalse { classFinder.matchName("ignoreThis.FooBarBaz", parsePattern("ignoreThis")) }
    }

    @Test
    fun patternMatch_searchWordsByStart() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("FoBa")) }
    }

    @Test
    fun patternMatch_searchFirstLetterAndFullWord() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("FBar")) }
    }

    @Test
    fun patternMatch_skipWord() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("FooBaz")) }
    }

    @Test
    fun patternMatch_patternEndsWithSpace() {
        assertTrue { classFinder.matchName("a.b.FooBar", parsePattern("Bar ")) }
    }

    @Test
    fun patternMatch_patternEndsWithSpace_notLastWord() {
        assertFalse { classFinder.matchName("a.b.FooBarBaz", parsePattern("Bar ")) }
    }

    @Test
    fun patternMatch_missingLetterInWord() {
        assertFalse { classFinder.matchName("a.b.BarBaz", parsePattern("BrBaz")) }
    }

    @Test
    fun patternMatch_asterisk_missingLetter() {
        assertTrue { classFinder.matchName("a.b.BarBaz", parsePattern("B*rBaz")) }
    }

    @Test
    fun patternMatch_asterisk_multipleMissingLetters() {
        assertTrue { classFinder.matchName("a.b.BabccrBaz", parsePattern("B*rBaz")) }
    }

    @Test
    fun patternMatch_asterisk_multipleMissingLetters_inSeparateWords() {
        assertTrue { classFinder.matchName("a.b.FooBar", parsePattern("F*r")) }
    }

    @Test
    fun patternMatch_asterisk_noMissingLetter() {
        assertTrue { classFinder.matchName("a.b.BrBaz", parsePattern("B*rBaz")) }
    }

    @Test
    fun patternMatch_asterisk_noMatch() {
        assertFalse { classFinder.matchName("a.b.BarBaz", parsePattern("B*zB")) }
    }

    @Test
    fun patternMatch_asterisk_noMatchInNonAsteriskWord() {
        assertFalse { classFinder.matchName("a.b.BarBaz", parsePattern("B*rA")) }
    }

    @Test
    fun patternMatch_caseSensitive() {
        assertFalse { classFinder.matchName("a.b.FooBar", parsePattern("fB")) }
    }

    @Test
    fun patternMatch_caseInsensitive_searchWordsByFirstLetter() {
        assertTrue { classFinder.matchName("a.b.FooBar", parsePattern("fb")) }
    }

    @Test
    fun patternMatch_caseInsensitive_doesNotFind() {
        assertFalse { classFinder.matchName("a.b.FooBar", parsePattern("oao")) }
    }

    @Test
    fun patternMatch_caseInsensitive_searchSingleSegment() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("oba")) }
    }

    @Test
    fun patternMatch_caseInsensitive_searchMultipleSegments() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("obaba")) }
    }

    @Test
    fun patternMatch_caseInsensitive_endsWithSpace() {
        assertTrue { classFinder.matchName("a.b.FooBar", parsePattern("ar ")) }
    }

    @Test
    fun patternMatch_caseInsensitive_endsWithSpace_notLastWord() {
        assertFalse { classFinder.matchName("a.b.FooBarBaz", parsePattern("ar ")) }
    }

    @Test
    fun patternMatch_caseInsensitive_asteriskIsIgnored() {
        assertTrue { classFinder.matchName("a.b.FooBarBaz", parsePattern("ob*a")) }
    }

    @Test
    fun patternMatch_caseInsensitive_ignorePackageName() {
        assertFalse { classFinder.matchName("ignore.FooBarBaz", parsePattern("ignore")) }
    }


}