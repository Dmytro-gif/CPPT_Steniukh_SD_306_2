package KI306.Steniukh.Lab5;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Запис/читання списку ResultRecord у текстовому та двійковому форматах.
 *
 * Текстовий формат (використовуємо формат Lab4):
 * для кожного запису — два рядки:
 *   x (deg) = <value>
 *   y = 1 / cos(4x) = <value>
 *
 *
 * Бінарний формат: int count, потім count*(double x, double y).
 */
public class ResultIO {

    /**
     * Записує список у текстовий файл у форматі (по 2 рядки на запис).
     */
    public static void writeText(List<ResultRecord> list, File file) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (ResultRecord r : list) {
            	pw.println("x (deg) = " + Double.toString(r.getX()));
            	pw.println("y = 1 / cos(4x) = " + Double.toString(r.getY()));
            }
            pw.flush();
        }
    }

    /**
     * Читання із текстового файлу:
     * - пара чисел у одному рядку: "x y"
     *
     * Якщо у файлі кілька записів — поверне їх усі у порядку зчитування.
     */
    public static List<ResultRecord> readText(File file) throws FileNotFoundException {
        List<ResultRecord> out = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("[-+]?(?:\\d*\\.\\d+|\\d+)(?:[eE][-+]?\\d+)?");

        try (Scanner sc = new Scanner(file)) {
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) lines.add(sc.nextLine());

            int i = 0;
            while (i < lines.size()) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) { i++; continue; }

                List<Double> nums = extractNumbers(line, numberPattern);

                if (nums.size() >= 2) {
                    // випадок "x y" або інший рядок з >=2 чисел:
                    // в загальному випадку беремо перші два числа як (x,y)
                    out.add(new ResultRecord(nums.get(0), nums.get(1)));
                    i++;
                } else if (nums.size() == 1) {
                    // формат Lab4: x в цьому рядку, а y — в наступному рядку
                    double x = nums.get(0);
                    double y = 0;
                    boolean foundY = false;
                    if (i + 1 < lines.size()) {
                        List<Double> nextNums = extractNumbers(lines.get(i + 1), numberPattern);
                        if (nextNums.size() >= 1) {
                            
                            y = nextNums.get(nextNums.size() - 1);
                            foundY = true;
                        }
                    }
                    if (foundY) {
                        out.add(new ResultRecord(x, y));
                        i += 2;
                    } else {
                        // якщо y не знайдено — пропускаємо
                        i++;
                    }
                } else {
                    i++;
                }
            }
        }

        return out;
    }


    private static List<Double> extractNumbers(String s, Pattern p) {
        List<Double> nums = new ArrayList<>();
        Matcher m = p.matcher(s);
        while (m.find()) {
            try { nums.add(Double.parseDouble(m.group())); }
            catch (NumberFormatException ignored) {}
        }
        return nums;
    }


    /**
     * Запис у бінарний файл: int count, потім count*(double x, double y).
     */
    public static void writeBinary(List<ResultRecord> list, File file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            dos.writeInt(list.size());
            for (ResultRecord r : list) {
                dos.writeDouble(r.getX());
                dos.writeDouble(r.getY());
            }
            dos.flush();
        }
    }

    /**
     * Читання з бінарного файлу (у форматі writeBinary).
     */
    public static List<ResultRecord> readBinary(File file) throws IOException {
        List<ResultRecord> out = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                double x = dis.readDouble();
                double y = dis.readDouble();
                out.add(new ResultRecord(x, y));
            }
        }
        return out;
    }
}
