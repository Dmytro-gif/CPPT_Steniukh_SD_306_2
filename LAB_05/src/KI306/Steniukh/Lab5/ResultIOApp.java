package KI306.Steniukh.Lab5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Драйвер: вводимо X (як у Lab4), обчислюємо y, записуємо у текстовий файл 
 * та у бінарний файл, читаємо назад і перевіряємо коректність.
 */
public class ResultIOApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Введіть базове ім'я файлу (без розширення): ");
            String base = in.nextLine().trim();
            if (base.isEmpty()) {
                System.err.println("Ім'я файлу не може бути пустим.");
                return;
            }
            File textFile = new File(base + ".txt");
            File binFile = new File(base + ".bin");

            System.out.print("Введіть X (в градусах): ");
            String xLine = in.nextLine().trim();
            double x;
            try {
                x = Double.parseDouble(xLine);
            } catch (NumberFormatException nfe) {
                System.err.println("Невірний формат числа.");
                return;
            }

            Equations eq = new Equations();
            double y;
            try {
                y = eq.calculate(x);
            } catch (CalcException ce) {
                System.err.println("Помилка обчислення: " + ce.getMessage());
                // Запишемо повідомлення у текстовий файл так само як у Lab4
                try (java.io.PrintWriter pw = new java.io.PrintWriter(textFile)) {
                    pw.println("Помилка обчислення: " + ce.getMessage());
                    pw.flush();
                } catch (IOException ioe) {
                    System.err.println("Не вдалося записати файл помилки: " + ioe.getMessage());
                }
                return;
            }

            // Підготуємо список з одного запису
            List<ResultRecord> list = new ArrayList<>();
            list.add(new ResultRecord(x, y));

            // Запис у текстовий та бінарний файли
            try {
                ResultIO.writeText(list, textFile);   // формат Lab4
                ResultIO.writeBinary(list, binFile);
                System.out.println("Записано 1 запис у: " + textFile.getAbsolutePath() + ", " + binFile.getAbsolutePath());
            } catch (IOException ioe) {
                System.err.println("Помилка запису у файл: " + ioe.getMessage());
                return;
            }

            // Зчитування назад і перевірка
            try {
                List<ResultRecord> fromText = ResultIO.readText(textFile);
                List<ResultRecord> fromBin = ResultIO.readBinary(binFile);

                boolean textOk = compareLists(list, fromText);
                boolean binOk = compareLists(list, fromBin);

                System.out.println("Текстовий файл відновлено коректно: " + textOk);
                System.out.println("Бінарний файл відновлено коректно: " + binOk);

                if (!textOk) {
                    System.out.println("Оригінал:");
                    list.forEach(System.out::println);
                    System.out.println("Зчитано з тексту:");
                    fromText.forEach(System.out::println);
                }
                if (!binOk) {
                    System.out.println("Зчитано з бінарного:");
                    fromBin.forEach(System.out::println);
                }
            } catch (IOException ioe) {
                System.err.println("Помилка читання файлу: " + ioe.getMessage());
                ioe.printStackTrace();
            }

        } finally {
            in.close();
        }
    }

    private static boolean compareLists(List<ResultRecord> a, List<ResultRecord> b) {
        if (b == null) return false;
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) return false;
        }
        return true;
    }
}
