package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Клас Lab1SteniukhKI210 будує квадратну матрицю з двома симетричними трикутниками.
 * Користувач вводить розмірність, символ трикутників і символ фону.
 *
 * @author Стенюх Дмитро
 * @version 1.0
 * @since 2025
 */

public class Lab1SteniukhKI210 {
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

        
        char[][] arr = new char[n][n];

        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                boolean left = j <= i && j <= n - 1 - i;
                
                boolean right = j >= i && j >= n - 1 - i;

                if (left || right) {
                    arr[i][j] = tri;
                } else {
                    arr[i][j] = bg;
                }
            }
        }

        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
                fout.print(arr[i][j] + " ");
            }
            System.out.println();
            fout.println();
        }

        fout.flush();
        fout.close();
        in.close();
    }
}
