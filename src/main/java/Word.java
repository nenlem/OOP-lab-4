/**
 * Представляє слово як масив об'єктів {@link Letter}.
 * Слово містить лише алфавітні символи.
 */
public class Word {

    /** Літери, які формують це слово. */
    private final Letter[] letters;

    /**
     * Створює об'єкт Word з масиву об'єктів Letter.
     *
     * @param letters масив літер, що формують слово
     * @throws IllegalArgumentException якщо масив letters є null
     */
    public Word(Letter[] letters) {
        if (letters == null) {
            throw new IllegalArgumentException("Letters array must not be null");
        }
        this.letters = letters.clone();
    }

    /**
     * Створює об'єкт Word зі звичайного рядка.
     * Кожен символ рядка стає об'єктом {@link Letter}.
     *
     * @param word рядок для перетворення у Word
     * @throws IllegalArgumentException якщо word є null
     */
    public Word(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word string must not be null");
        }
        this.letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            this.letters[i] = new Letter(word.charAt(i));
        }
    }

    /**
     * Повертає кількість літер у цьому слові.
     *
     * @return довжина слова
     */
    public int length() {
        return letters.length;
    }

    /**
     * Повертає літеру за вказаним індексом.
     *
     * @param index позиція літери
     * @return Letter за заданим індексом
     */
    public Letter getLetter(int index) {
        return letters[index];
    }

    /**
     * Повертає копію всіх літер у цьому слові.
     *
     * @return масив об'єктів Letter
     */
    public Letter[] getLetters() {
        return letters.clone();
    }

    /**
     * Повертає рядкове представлення цього слова шляхом об'єднання всіх значень літер.
     *
     * @return слово як звичайний рядок
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.getValue());
        }
        return sb.toString();
    }

    /**
     * Перевіряє рівність на основі вмісту літер.
     *
     * @param obj об'єкт для порівняння
     * @return true, якщо обидва слова мають ідентичні послідовності літер
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Word)) {
            return false;
        }
        Word other = (Word) obj;
        if (this.letters.length != other.letters.length) {
            return false;
        }
        for (int i = 0; i < letters.length; i++) {
            if (!this.letters[i].equals(other.letters[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Повертає хеш-код для цього слова на основі його рядкового значення.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}