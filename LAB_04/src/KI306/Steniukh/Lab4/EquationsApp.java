package KI306.Steniukh.Lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Драйвер для лабораторної 4: читає X, обчислює y = 1/cos(4x) і записує у файл.
 */
public class EquationsApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            System.out.print("Введіть ім'я файлу для запису результату: ");
            String fName = in.nextLine().trim();
            if (fName.isEmpty()) {
                System.out.println("Ім'я файлу не може бути пустим.");
                return;
            }

            try (PrintWriter fout = new PrintWriter(new File(fName))) {
                System.out.print("Введіть X (в градусах): ");
                String xLine = in.nextLine().trim();

                double x;
                try {
                    x = Double.parseDouble(xLine);
                } catch (NumberFormatException nfe) {
                    System.err.println("Помилка: невірний формат числа для X.");
                    return;
                }

                Equations eq = new Equations();
                try {
                    double y = eq.calculate(x);
                    fout.printf("x (deg) = %.6f%n", x);
                    fout.printf("y = 1 / cos(4x) = %.10f%n", y);
                    fout.flush();
                    System.out.println("Обчислення успішне. Результат записаний у файл: " + (new File(fName)).getAbsolutePath());
                } catch (CalcException ce) {
                    System.err.println("Помилка обчислення: " + ce.getMessage());
                    fout.println("Помилка обчислення: " + ce.getMessage());
                    fout.flush();
                }
            } // fout закриється тут
        } catch (FileNotFoundException fnfe) {
            System.err.println("Не вдалося відкрити файл для запису: " + fnfe.getMessage());
        } finally {
            in.close();
        }
    }
}
