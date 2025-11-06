package KI306.Steniukh.Lab4;

/**
 * Клас Equations реалізує метод обчислення y = 1 / cos(4x).
 *
 * @author Стенюх Дмитро
 * @version 1.0
 * @since 2025
 */
public class Equations {

    /**
     * Обчислює y = 1 / cos(4x).
     *
     * @param x кут у градусах
     * @return значення y
     * @throws CalcException якщо cos(4x) близький до нуля або результат некоректний
     */
    public double calculate(double x) throws CalcException {
        double rad = Math.toRadians(x);
        double cos4x = Math.cos(4.0 * rad);

        double eps = 1e-15;
        if (Double.isNaN(cos4x) || Double.isInfinite(cos4x) || Math.abs(cos4x) < eps) {
            throw new CalcException("Недопустиме значення: cos(4x) = " + cos4x + " (можливе ділення на нуль) для x = " + x);
        }

        double y = 1.0 / cos4x;
        if (Double.isNaN(y) || Double.isInfinite(y)) {
            throw new CalcException("Результат не є скінченним: y = " + y + " для x = " + x);
        }

        return y;
    }
}
