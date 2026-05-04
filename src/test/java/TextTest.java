import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Юніт-тести для {@link Text}.
 */
class TextTest {

    @Test
    void constructor_null_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Text(null));
    }

    @Test
    void sentenceCount_singleSentence() {
        Text text = new Text("Hello world.");
        assertEquals(1, text.sentenceCount());
    }

    @Test
    void sentenceCount_multipleSentences() {
        Text text = new Text("Hello world. How are you? I am fine!");
        assertEquals(3, text.sentenceCount());
    }

    @Test
    void sentenceCount_noTerminalPunctuation_singleSentence() {
        Text text = new Text("No punctuation here");
        assertEquals(1, text.sentenceCount());
    }

    @Test
    void getSentence_returnsCorrectSentence() {
        Text text = new Text("First. Second.");
        assertEquals("First.", text.getSentence(0).toString());
    }

    @Test
    void toString_singleSentence_preserved() {
        String raw = "The cat sat on the mat.";
        assertEquals(raw, new Text(raw).toString());
    }

    @Test
    void toString_multipleSentences_preserved() {
        String raw = "Hello world. How are you?";
        assertEquals(raw, new Text(raw).toString());
    }

    @Test
    void replaceWordsOfLength_standardCase_acrossSingleSentence() {
        Text text = new Text(
                "The golden sunset painted the evening sky in bright shades of orange and pink.");
        text.replaceWordsOfLength(3, "Car");
        assertEquals(
                "Car golden sunset painted Car evening Car in bright shades of orange Car pink.",
                text.toString()
        );
    }

    @Test
    void replaceWordsOfLength_acrossMultipleSentences() {
        Text text = new Text("The cat sat. The dog ran.");
        text.replaceWordsOfLength(3, "XXX");
        assertEquals("XXX XXX XXX. XXX XXX XXX.", text.toString());
    }

    @Test
    void replaceWordsOfLength_noMatch_unchanged() {
        Text text = new Text("Beautiful architecture.");
        text.replaceWordsOfLength(3, "Car");
        assertEquals("Beautiful architecture.", text.toString());
    }

    @Test
    void replaceWordsOfLength_zeroLength_throwsException() {
        Text text = new Text("Hello world.");
        assertThrows(IllegalArgumentException.class,
                () -> text.replaceWordsOfLength(0, "X"));
    }

    @Test
    void replaceWordsOfLength_negativeLength_throwsException() {
        Text text = new Text("Hello world.");
        assertThrows(IllegalArgumentException.class,
                () -> text.replaceWordsOfLength(-5, "X"));
    }

    @Test
    void replaceWordsOfLength_emptyText_noException() {
        Text text = new Text("");
        assertDoesNotThrow(() -> text.replaceWordsOfLength(3, "Car"));
        assertEquals("", text.toString());
    }

    @Test
    void replaceWordsOfLength_replacementWithDifferentLength() {
        Text text = new Text("a cat and a dog.");
        text.replaceWordsOfLength(3, "elephant");
        assertEquals("a elephant and a elephant.", text.toString());
    }

    @Test
    void tabsAndMultipleSpaces_normalisedToSingleSpace() {
        Text text = new Text("A  cat\there.");
        text.replaceWordsOfLength(3, "dog");
        assertEquals("A dog here.", text.toString());
    }

    @Test
    void getSentences_returnsDefensiveCopy() {
        Text text = new Text("Hello world.");
        Sentence[] copy = text.getSentences();
        // Mutating the copy should not affect the Text
        copy[0] = new Sentence("Changed.");
        assertEquals("Hello world.", text.getSentence(0).toString());
    }

    @Test
    void equals_sameTexts_true() {
        assertEquals(new Text("Hello world."), new Text("Hello world."));
    }

    @Test
    void equals_differentTexts_false() {
        assertNotEquals(new Text("Hello."), new Text("World."));
    }

    @Test
    void hashCode_equalTexts_equalHashCodes() {
        assertEquals(
                new Text("hello world.").hashCode(),
                new Text("hello world.").hashCode()
        );
    }
}