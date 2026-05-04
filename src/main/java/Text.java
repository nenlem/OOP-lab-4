/**
 * Представляє текст як масив об'єктів {@link Sentence}.
 *
 * <p>Текст розбивається на речення за розділовими знаками кінця речення ({@code .}, {@code !},
 * {@code ?}), при цьому ці роздільники залишаються приєднаними до попереднього речення.
 * Пробіл між реченнями зберігається як початковий пробіл у наступному токені речення.</p>
 */
public class Text {

    /** Упорядкований масив речень, які формують цей текст. */
    private final Sentence[] sentences;

    /**
     * Створює об'єкт Text шляхом розбиття вихідного рядка на об'єкти {@link Sentence}.
     * Кожне речення включає свій кінцевий знак пунктуації.
     *
     * @param rawText вихідний текст із кількох речень
     * @throws IllegalArgumentException якщо rawText є null
     */
    public Text(String rawText) {
        if (rawText == null) {
            throw new IllegalArgumentException("Text must not be null");
        }
        this.sentences = parseText(rawText);
    }

    /**
     * Розбиває вихідний текст на об'єкти {@link Sentence}.
     * Межі речень визначаються після {@code .}, {@code !} та {@code ?}.
     *
     * @param raw вихідний рядок тексту
     * @return масив об'єктів Sentence
     */
    private Sentence[] parseText(String raw) {
        java.util.List<Sentence> list = new java.util.ArrayList<>();
        int start = 0;
        for (int i = 0; i < raw.length(); i++) {
            char ch = raw.charAt(i);
            if (ch == '.' || ch == '!' || ch == '?') {
                // Include the punctuation character in the current sentence
                String chunk = raw.substring(start, i + 1);
                if (!chunk.trim().isEmpty()) {
                    list.add(new Sentence(chunk));
                }
                start = i + 1;
            }
        }
        // Remaining text without terminal punctuation (if any)
        if (start < raw.length()) {
            String chunk = raw.substring(start);
            if (!chunk.trim().isEmpty()) {
                list.add(new Sentence(chunk));
            }
        }
        if (list.isEmpty()) {
            list.add(new Sentence(raw));
        }
        return list.toArray(new Sentence[0]);
    }

    /**
     * Повертає кількість речень у цьому тексті.
     *
     * @return кількість речень
     */
    public int sentenceCount() {
        return sentences.length;
    }

    /**
     * Повертає речення за вказаним індексом.
     *
     * @param index позиція речення
     * @return Sentence за заданим індексом
     */
    public Sentence getSentence(int index) {
        return sentences[index];
    }

    /**
     * Повертає копію всіх речень у цьому тексті.
     *
     * @return масив об'єктів Sentence
     */
    public Sentence[] getSentences() {
        return sentences.clone();
    }

    /**
     * Замінює кожне слово заданої довжини на рядок-замінник у всіх реченнях.
     * Також нормалізує послідовні символи пробілу / табуляції до одного пробілу по всьому
     * тексту (ця трансформація вже обробляється всередині {@link Sentence}).
     *
     * @param searchLength  довжина слова для пошуку (має бути &gt; 0)
     * @param replacement   рядок для підстановки замість знайдених слів
     * @throws IllegalArgumentException якщо searchLength не є додатнім
     */
    public void replaceWordsOfLength(int searchLength, String replacement) {
        if (searchLength <= 0) {
            throw new IllegalArgumentException("Search length must be greater than 0");
        }
        for (Sentence sentence : sentences) {
            sentence.replaceWordsOfLength(searchLength, replacement);
        }
    }

    /**
     * Повертає весь текст як звичайний рядок, реконструйований з його речень.
     *
     * @return рядок тексту
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            sb.append(sentence.toString());
        }
        return sb.toString();
    }

    /**
     * Перевіряє рівність шляхом порівняння рядкових представлень.
     *
     * @param obj об'єкт для порівняння
     * @return true, якщо обидва тексти утворюють однаковий рядок
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Text)) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }

    /**
     * Повертає хеш-код на основі рядка тексту.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}