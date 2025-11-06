package KI306.Steniukh.Lab5;

/**
 * Спеціальне виключення для помилок обчислення.
 */
public class CalcException extends Exception {
    public CalcException() { super(); }
    public CalcException(String msg) { super(msg); }
}
