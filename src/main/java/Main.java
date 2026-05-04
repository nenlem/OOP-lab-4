/**
 * Головний виконуваний клас для завдання з модифікації тексту (Лабораторна робота 4).
 *
 * <p>Замінює кожне слово заданої довжини у вказаному тексті на рядок-замінник,
 * використовуючи ООП модель: {@link Letter} → {@link Word}
 * → {@link Sentence} → {@link Text}. Послідовні пробіли / табуляції
 * нормалізуються до одного пробілу відповідно до вимог завдання.</p>
 *
 * <p>Усі змінні оголошуються та ініціалізуються всередині методу main (виконуваного),
 * як того вимагає завдання.</p>
 */
public class Main {

    /**
     * Точка входу. Демонструє функцію модифікації тексту.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        // ── Variables declared and initialised in the executable method ──
        int searchLength = 3;
        String replacementStr = "Car";
        String originalText =
                "The golden sunset painted the evening sky in bright shades of orange and pink.";

        try {
            if (searchLength <= 0) {
                throw new IllegalArgumentException("Search length must be greater than 0");
            }

            Text text = new Text(originalText);
            text.replaceWordsOfLength(searchLength, replacementStr);

            System.out.println("Source: " + originalText);
            System.out.println("Result: " + text.toString());

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
        }
    }
}