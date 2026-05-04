import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Юніт-тести для {@link Word}.
 */
class WordTest {

    @Test
    void constructor_fromString_lengthIsCorrect() {
        Word word = new Word("hello");
        assertEquals(5, word.length());
    }

    @Test
    void constructor_fromString_toString() {
        assertEquals("hello", new Word("hello").toString());
    }

    @Test
    void constructor_fromLetterArray_toString() {
        Letter[] letters = {new Letter('c'), new Letter('a'), new Letter('t')};
        assertEquals("cat", new Word(letters).toString());
    }

    @Test
    void constructor_fromString_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Word((String) null));
    }

    @Test
    void constructor_fromLetterArray_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Word((Letter[]) null));
    }

    @Test
    void getLetter_correctCharAtIndex() {
        Word word = new Word("dog");
        assertEquals('d', word.getLetter(0).getValue());
        assertEquals('o', word.getLetter(1).getValue());
        assertEquals('g', word.getLetter(2).getValue());
    }

    @Test
    void getLetters_returnsDefensiveCopy() {
        Word word = new Word("cat");
        Letter[] copy = word.getLetters();
        copy[0] = new Letter('X');
        // Original should be unchanged
        assertEquals('c', word.getLetter(0).getValue());
    }

    @Test
    void length_emptyString_zero() {
        assertEquals(0, new Word("").length());
    }

    @Test
    void equals_sameContent_true() {
        assertEquals(new Word("sun"), new Word("sun"));
    }

    @Test
    void equals_differentContent_false() {
        assertNotEquals(new Word("sun"), new Word("moon"));
    }

    @Test
    void equals_differentLength_false() {
        assertNotEquals(new Word("cat"), new Word("cats"));
    }

    @Test
    void equals_sameInstance_true() {
        Word w = new Word("fox");
        assertEquals(w, w);
    }

    @Test
    void equals_nonWordObject_false() {
        assertNotEquals(new Word("fox"), "fox");
    }

    @Test
    void hashCode_equalWords_equalHashCodes() {
        assertEquals(new Word("tree").hashCode(), new Word("tree").hashCode());
    }
}