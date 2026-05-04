/**
 * Представляє один символ (літеру) в тексті.
 * Зберігає значення символу та надає базові операції над ним.
 */
public class Letter {

    /** Значення символу цієї літери. */
    private final char value;

    /**
     * Створює об'єкт Letter із заданим значенням символу.
     *
     * @param value символ для зберігання
     */
    public Letter(char value) {
        this.value = value;
    }

    /**
     * Повертає значення символу цієї літери.
     *
     * @return значення символу
     */
    public char getValue() {
        return value;
    }

    /**
     * Повертає, чи є ця літера алфавітним символом.
     *
     * @return true, якщо символ є літерою, інакше false
     */
    public boolean isAlphabetic() {
        return Character.isLetter(value);
    }

    /**
     * Повертає рядкове представлення цієї літери (рядок з одного символу).
     *
     * @return рядок, що містить лише цей символ
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Перевіряє рівність на основі значення символу.
     *
     * @param obj об'єкт для порівняння
     * @return true, якщо інший об'єкт є Letter з таким самим значенням
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Letter)) {
            return false;
        }
        Letter other = (Letter) obj;
        return this.value == other.value;
    }

    /**
     * Повертає хеш-код для цієї літери.
     *
     * @return хеш-код на основі значення символу
     */
    @Override
    public int hashCode() {
        return Character.hashCode(value);
    }
}