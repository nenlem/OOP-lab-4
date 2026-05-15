/**
 * Представляє речення як масив об'єктів {@link Word} та символів пунктуації/пробілів.
 *
 * Речення моделюється як послідовність токенів, де кожен токен є або
 * {@link Word}, або одиничним {@link Letter}, що слугує
 * роздільником або знаком пунктуації.
 */
public class Sentence {

    /**
     * Внутрішній токен, який містить або {@link Word}, або роздільник {@link Letter}.
     */
    private static class Token {
        final Word word;
        final Letter separator;

        Token(Word word) {
            this.word = word;
            this.separator = null;
        }

        Token(Letter separator) {
            this.word = null;
            this.separator = separator;
        }

        boolean isWord() {
            return word != null;
        }

        @Override
        public String toString() {
            return isWord() ? word.toString() : String.valueOf(separator.getValue());
        }
    }

    /** Упорядкована послідовність токенів (слів та роздільників) у цьому реченні. */
    private final Token[] tokens;

    /**
     * Створює об'єкт Sentence шляхом розбору звичайного рядка на слова та символи-роздільники.
     * Послідовні пробільні символи згортаються в один токен пробілу.
     *
     * @param sentence вихідний рядок речення
     * @throws IllegalArgumentException якщо sentence є null
     */
    public Sentence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence string must not be null");
        }
        this.tokens = parseSentence(sentence);
    }

    /**
     * Розбирає вихідний рядок у масив об'єктів {@link Token}.
     * Замінює будь-яку послідовність пробілів / табуляцій на один токен пробілу.
     *
     * @param raw вихідний рядок речення
     * @return масив токенів
     */
    private Token[] parseSentence(String raw) {
        String normalized = raw.replaceAll("[ \\t]+", " ");

        java.util.List<Token> list = new java.util.ArrayList<>();
        int i = 0;
        while (i < normalized.length()) {
            char ch = normalized.charAt(i);
            if (Character.isLetter(ch)) {
                int start = i;
                while (i < normalized.length() && Character.isLetter(normalized.charAt(i))) {
                    i++;
                }
                list.add(new Token(new Word(normalized.substring(start, i))));
            } else {
                list.add(new Token(new Letter(ch)));
                i++;
            }
        }
        return list.toArray(new Token[0]);
    }

    /**
     * Повертає кількість токенів (слів + роздільників) у цьому реченні.
     *
     * @return загальна кількість токенів
     */
    public int tokenCount() {
        return tokens.length;
    }

    /**
     * Повертає лише об'єкти {@link Word}, що містяться в цьому реченні, по порядку.
     *
     * @return масив слів (без роздільників)
     */
    public Word[] getWords() {
        int count = 0;
        for (Token t : tokens) {
            if (t.isWord()) {
                count++;
            }
        }
        Word[] words = new Word[count];
        int idx = 0;
        for (Token t : tokens) {
            if (t.isWord()) {
                words[idx++] = t.word;
            }
        }
        return words;
    }

    /**
     * Замінює кожне слово, довжина якого дорівнює {@code searchLength}, на {@code replacement}.
     *
     * @param searchLength  довжина слова для пошуку
     * @param replacement   рядок слова для заміни
     * @throws IllegalArgumentException якщо searchLength не є додатнім
     */
    public void replaceWordsOfLength(int searchLength, String replacement) {
        if (searchLength <= 0) {
            throw new IllegalArgumentException("Search length must be greater than 0");
        }
        for (int i = 0; i < tokens.length; i++) {
            Token t = tokens[i];
            if (t.isWord() && t.word.length() == searchLength) {
                tokens[i] = new Token(new Word(replacement));
            }
        }
    }

    /**
     * Повертає речення як звичайний рядок, реконструйований з його токенів.
     *
     * @return рядок речення
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token t : tokens) {
            sb.append(t.toString());
        }
        return sb.toString();
    }

    /**
     * Перевіряє рівність шляхом порівняння рядкових представлень.
     *
     * @param obj об'єкт для порівняння
     * @return true, якщо обидва речення утворюють однаковий рядок
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sentence)) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }

    /**
     * Повертає хеш-код на основі рядка речення.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}