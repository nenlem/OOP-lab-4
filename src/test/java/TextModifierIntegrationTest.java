import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Інтеграційні тести, які перевіряють повний конвеєр Letter → Word → Sentence → Text
 * та підтверджують наскрізну поведінку {@link TextModifierOOP}.
 */
class TextModifierIntegrationTest {

    // ── Full-pipeline replacement ────────────────────────────────────────────

    @Test
    void fullPipeline_labAssignmentExample() {
        Text text = new Text(
                "The golden sunset painted the evening sky in bright shades of orange and pink.");
        text.replaceWordsOfLength(3, "Car");
        assertEquals(
                "Car golden sunset painted Car evening Car in bright shades of orange Car pink.",
                text.toString()
        );
    }

    @Test
    void fullPipeline_punctuationAdjacentToWord() {
        Text text = new Text("Cat, meet dog.");
        text.replaceWordsOfLength(3, "Bird");
        assertEquals("Bird, meet Bird.", text.toString());
    }

    @Test
    void fullPipeline_emptyString() {
        Text text = new Text("");
        text.replaceWordsOfLength(3, "Car");
        assertEquals("", text.toString());
    }

    @Test
    void fullPipeline_noWordsOfTargetLength() {
        Text text = new Text("Beautiful architecture.");
        text.replaceWordsOfLength(3, "Car");
        assertEquals("Beautiful architecture.", text.toString());
    }

    @Test
    void fullPipeline_onlyDigitsAndSymbols() {
        Text text = new Text("123 ... 456!");
        text.replaceWordsOfLength(3, "Car");
        assertEquals("123 ... 456!", text.toString());
    }

    @Test
    void fullPipeline_multipleSpaces_collapsedToOne() {
        Text text = new Text("A  cat   is  here.");
        text.replaceWordsOfLength(3, "dog");
        assertEquals("A dog is here.", text.toString());
    }

    @Test
    void fullPipeline_singleWord() {
        Text text = new Text("Cat");
        text.replaceWordsOfLength(3, "Bird");
        assertEquals("Bird", text.toString());
    }

    @Test
    void fullPipeline_replacementLongerThanSearchWord() {
        Text text = new Text("a cat and a dog.");
        text.replaceWordsOfLength(3, "elephant");
        assertEquals("a elephant and a elephant.", text.toString());
    }

    @Test
    void fullPipeline_multiSentenceText() {
        Text text = new Text("The cat sat. The big dog ran.");
        text.replaceWordsOfLength(3, "XXX");
        assertEquals("XXX XXX XXX. XXX big XXX XXX.", text.toString());
    }

    @Test
    void fullPipeline_replacementShorterThanSearchWord() {
        Text text = new Text("Cats enjoy sleep.");
        text.replaceWordsOfLength(4, "X");
        assertEquals("X enjoy X.", text.toString());
    }

    // ── Exception validation ─────────────────────────────────────────────────

    @Test
    void replaceWordsOfLength_zeroSearchLength_throwsException() {
        Text text = new Text("Hello world.");
        assertThrows(IllegalArgumentException.class,
                () -> text.replaceWordsOfLength(0, "X"));
    }

    @Test
    void replaceWordsOfLength_negativeSearchLength_throwsException() {
        Text text = new Text("Hello world.");
        assertThrows(IllegalArgumentException.class,
                () -> text.replaceWordsOfLength(-3, "X"));
    }

    @Test
    void text_nullInput_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Text(null));
    }

    @Test
    void sentence_nullInput_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Sentence(null));
    }

    @Test
    void word_nullString_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Word((String) null));
    }

    @Test
    void word_nullLetterArray_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Word((Letter[]) null));
    }

    // ── OOP structure integrity ──────────────────────────────────────────────

    @Test
    void sentence_getWords_containsExpectedWords() {
        Sentence sentence = new Sentence("The quick brown fox.");
        Word[] words = sentence.getWords();
        assertEquals(4, words.length);
        assertEquals("The", words[0].toString());
        assertEquals("quick", words[1].toString());
        assertEquals("brown", words[2].toString());
        assertEquals("fox", words[3].toString());
    }

    @Test
    void text_sentenceCount_multipleExclamation() {
        Text text = new Text("Run! Jump! Fly!");
        assertEquals(3, text.sentenceCount());
    }

    @Test
    void word_builtFromLetterArray_equalToStringConstructor() {
        Letter[] letters = {new Letter('c'), new Letter('a'), new Letter('t')};
        Word fromArray = new Word(letters);
        Word fromString = new Word("cat");
        assertEquals(fromString, fromArray);
    }

    @Test
    void letter_isAlphabetic_uppercaseAndLowercase() {
        assertTrue(new Letter('A').isAlphabetic());
        assertTrue(new Letter('z').isAlphabetic());
    }
}