import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Юніт-тести для {@link Letter}.
 */
class LetterTest {

    @Test
    void getValue_returnsStoredChar() {
        Letter letter = new Letter('A');
        assertEquals('A', letter.getValue());
    }

    @Test
    void isAlphabetic_trueForLetter() {
        assertTrue(new Letter('z').isAlphabetic());
    }

    @Test
    void isAlphabetic_falseForDigit() {
        assertFalse(new Letter('5').isAlphabetic());
    }

    @Test
    void isAlphabetic_falseForSpace() {
        assertFalse(new Letter(' ').isAlphabetic());
    }

    @Test
    void isAlphabetic_falseForPunctuation() {
        assertFalse(new Letter('.').isAlphabetic());
    }

    @Test
    void toString_returnsSingleCharString() {
        assertEquals("X", new Letter('X').toString());
    }

    @Test
    void equals_sameChar_true() {
        assertEquals(new Letter('a'), new Letter('a'));
    }

    @Test
    void equals_differentChar_false() {
        assertNotEquals(new Letter('a'), new Letter('b'));
    }

    @Test
    void equals_nonLetterObject_false() {
        assertNotEquals(new Letter('a'), "a");
    }

    @Test
    void equals_sameInstance_true() {
        Letter l = new Letter('k');
        assertEquals(l, l);
    }

    @Test
    void hashCode_equalLetters_equalHashCodes() {
        assertEquals(new Letter('m').hashCode(), new Letter('m').hashCode());
    }
}