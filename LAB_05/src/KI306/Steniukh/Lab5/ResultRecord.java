package KI306.Steniukh.Lab5;

import java.util.Objects;

/**
 * Контейнер для результату обчислення: пара (x, y).
 */
public class ResultRecord {
    public double x;
    public double y;

    public ResultRecord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public String toString() {
        return String.format("x=%.10f y=%.10f", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultRecord)) return false;
        ResultRecord that = (ResultRecord) o;
        double EPS = 1e-15;
        return Math.abs(this.x - that.x) < EPS && Math.abs(this.y - that.y) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
