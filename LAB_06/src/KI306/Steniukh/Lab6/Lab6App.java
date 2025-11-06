package KI306.Steniukh.Lab6;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Драйвер для лабораторної 6.
 * Створює контейнер місткістю 9, додає до нього щонайменше 2 різні класи
 * (Banka та Tin), демонструє операції вставки, видалення, пошуку мінімального елементу.
 */
public class Lab6App {
    public static void main(String[] args) {
        // Створюємо контейнер місткістю 9
        GenericContainer<Product> container = new GenericContainer<>(9);

        // Додаємо 9 елементів — суміш Banka та Tin
        container.add(new Banka("Горох", 400, 25.5, "aluminium"));
        container.add(new Tin("Риба", 240, 40.0, "OceanBrand"));
        container.add(new Banka("Кукурудза", 450, 20.0, "steel"));
        container.add(new Tin("Помідори", 500, 35.0, "TomatoCo"));
        container.add(new Banka("Компот", 350, 18.75, "aluminium"));
        container.add(new Tin("Сардини", 200, 28.0, "SeaKing"));
        container.add(new Banka("Фасоля", 425, 22.0, "steel"));
        container.add(new Tin("Паштет", 150, 30.0, "PateMaster"));
        container.add(new Banka("Варення", 300, 27.5, "aluminium"));

        // Виводимо вміст
        System.out.println("---- Початковий вміст контейнера ----");
        System.out.println(container);

        // Знаходимо мінімальний елемент (за ціною)
        Product min = container.findMin();
        System.out.println("Мінімальний елемент (за ціною): " + min);

        // Видалимо мінімальний елемент (демонстрація видалення)
        if (min != null) {
            System.out.println("Видаляємо мінімальний елемент...");
            container.remove(min);
        }

        System.out.println("---- Вміст після видалення мінімального ----");
        System.out.println(container);

        // Додаткова демонстрація: знаходимо зараз мінімальний
        Product newMin = container.findMin();
        System.out.println("Новий мінімальний елемент: " + newMin);

        // Записуємо результат у файл (для звіту)
        try (PrintWriter pw = new PrintWriter(new FileWriter("lab6_output.txt", false))) {
            pw.println("Контейнер після операцій:");
            pw.println(container.toString());
            pw.println("Перший мінімальний елемент (видалений): " + (min == null ? "none" : min.toString()));
            pw.println("Поточний мінімальний елемент: " + (newMin == null ? "none" : newMin.toString()));
            pw.flush();
            System.out.println("Результат записано у lab6_output.txt");
        } catch (IOException ioe) {
            System.err.println("Не вдалося записати файл: " + ioe.getMessage());
        }
    }
}
