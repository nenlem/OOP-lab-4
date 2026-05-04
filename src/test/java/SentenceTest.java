import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Юніт-тести для {@link Sentence}.
 */
class SentenceTest {

    @Test
    void constructor_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Sentence(null));
    }

    @Test
    void toString_simpleString_preserved() {
        Sentence s = new Sentence("Hello world");
        assertEquals("Hello world", s.toString());
    }

    @Test
    void toString_withPunctuation_preserved() {
        Sentence s = new Sentence("Cat, meet dog.");
        assertEquals("Cat, meet dog.", s.toString());
    }

    @Test
    void toString_emptyString_empty() {
        assertEquals("", new Sentence("").toString());
    }

    @Test
    void getWords_returnsOnlyWords() {
        Sentence s = new Sentence("Cat, meet dog.");
        Word[] words = s.getWords();
        assertEquals(3, words.length);
        assertEquals("Cat", words[0].toString());
        assertEquals("meet", words[1].toString());
        assertEquals("dog", words[2].toString());
    }

    @Test
    void getWords_noWords_emptyArray() {
        Sentence s = new Sentence("123 ... 456!");
        assertEquals(0, s.getWords().length);
    }

    @Test
    void replaceWordsOfLength_standardCase() {
        Sentence s = new Sentence("Cat, meet dog.");
        s.replaceWordsOfLength(3, "Bird");
        assertEquals("Bird, meet Bird.", s.toString());
    }

    @Test
    void replaceWordsOfLength_noMatch_unchanged() {
        Sentence s = new Sentence("Beautiful architecture");
        s.replaceWordsOfLength(3, "Car");
        assertEquals("Beautiful architecture", s.toString());
    }

    @Test
    void replaceWordsOfLength_replacementLongerThanWord() {
        Sentence s = new Sentence("a cat and a dog");
        s.replaceWordsOfLength(3, "elephant");
        assertEquals("a elephant and a elephant", s.toString());
    }

    @Test
    void replaceWordsOfLength_singleWord() {
        Sentence s = new Sentence("Cat");
        s.replaceWordsOfLength(3, "Bird");
        assertEquals("Bird", s.toString());
    }

    @Test
    void replaceWordsOfLength_zeroLength_throwsException() {
        Sentence s = new Sentence("hello world");
        assertThrows(IllegalArgumentException.class,
                () -> s.replaceWordsOfLength(0, "X"));
    }

    @Test
    void replaceWordsOfLength_negativeLength_throwsException() {
        Sentence s = new Sentence("hello world");
        assertThrows(IllegalArgumentException.class,
                () -> s.replaceWordsOfLength(-1, "X"));
    }

    @Test
    void multipleSpaces_collapsedToSingle() {
        Sentence s = new Sentence("A  cat   is  here");
        s.replaceWordsOfLength(3, "dog");
        assertEquals("A dog is here", s.toString());
    }

    @Test
    void tabCharacters_collapsedToSingleSpace() {
        Sentence s = new Sentence("A\tcat\there");
        s.replaceWordsOfLength(3, "dog");
        assertEquals("A dog here", s.toString());
    }

    @Test
    void replaceWordsOfLength_onlySymbolsAndDigits_unchanged() {
        Sentence s = new Sentence("123 ... 456!");
        s.replaceWordsOfLength(3, "Car");
        assertEquals("123 ... 456!", s.toString());
    }

    @Test
    void equals_sameSentences_true() {
        assertEquals(new Sentence("Hello world"), new Sentence("Hello world"));
    }

    @Test
    void equals_differentSentences_false() {
        assertNotEquals(new Sentence("Hello"), new Sentence("World"));
    }

    @Test
    void hashCode_equalSentences_equalHashCodes() {
        assertEquals(
                new Sentence("hello").hashCode(),
                new Sentence("hello").hashCode()
        );
    }
}