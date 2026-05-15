/**
 * Головний виконуваний клас для завдання з модифікації тексту (Лабораторна робота 4).
 *
 * Замінює кожне слово заданої довжини у вказаному тексті на рядок-замінник,
 * використовуючи ООП модель: {@link Letter} → {@link Word}
 * → {@link Sentence} → {@link Text}. Послідовні пробіли / табуляції
 * нормалізуються до одного пробілу відповідно до вимог завдання.
 *
 * Усі змінні оголошуються та ініціалізуються всередині методу main,
 * як того вимагає завдання.
 */
public class Main {

    /**
     * Точка входу. Демонструє функцію модифікації тексту.
     */
    public static void main(String[] args) {
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