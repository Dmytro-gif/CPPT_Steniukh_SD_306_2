package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Клас Lab1SteniukhKI306 будує квадратну матрицю з двома симетричними трикутниками.
 * Використовується зубчатий масив для економії пам'яті.
 *
 * @author Стенюх Дмитро
 * @version 1.2
 * @since 2025
 */
public class Lab1SteniukhKI306 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        File dataFile = new File("Triangles.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        System.out.print("Введіть розмір квадратної матриці: ");
        int n = in.nextInt();
        in.nextLine();

        System.out.print("Введіть символ для трикутників: ");
        String triStr = in.nextLine();
        if (triStr.length() != 1) {
            System.out.println("Помилка: треба ввести один символ!");
            return;
        }
        char tri = triStr.charAt(0);

        System.out.print("Введіть символ для фону: ");
        String bgStr = in.nextLine();
        if (bgStr.length() != 1) {
            System.out.println("Помилка: треба ввести один символ!");
            return;
        }
        char bg = bgStr.charAt(0);

        // зубчатий масив: у кожному рядку зберігаємо тільки символи трикутників
        char[][] arr = new char[n][];

        for (int i = 0; i < n; i++) {
            int leftEnd = Math.min(i, n - 1 - i);
            int rightStart = Math.max(i, n - 1 - i);

            int leftLen = leftEnd + 1;
            int rightLen = n - rightStart;
            int overlap = Math.max(0, leftEnd - rightStart + 1);

            int unique = leftLen + rightLen - overlap;
            arr[i] = new char[unique];

            // лівий сегмент
            for (int j = 0; j <= leftEnd; j++) {
                arr[i][j] = tri;
            }

            // правий сегмент
            int rightTargetStart = leftLen - overlap;
            for (int j = rightStart; j < n; j++) {
                arr[i][rightTargetStart + (j - rightStart)] = tri;
            }
        }

        // відтворення повної матриці під час друку
        for (int i = 0; i < n; i++) {
            int leftEnd = Math.min(i, n - 1 - i);
            int rightStart = Math.max(i, n - 1 - i);
            int leftLen = leftEnd + 1;
            int overlap = Math.max(0, leftEnd - rightStart + 1);
            int rightTargetStart = leftLen - overlap;

            for (int j = 0; j < n; j++) {
                char outChar;
                if (j <= leftEnd) {
                    outChar = arr[i][j];
                } else if (j >= rightStart) {
                    int idx = rightTargetStart + (j - rightStart);
                    outChar = arr[i][idx];
                } else {
                    outChar = bg;
                }
                System.out.print(outChar + " ");
                fout.print(outChar + " ");
            }
            System.out.println();
            fout.println();
        }

        fout.flush();
        fout.close();
        in.close();
    }
}
