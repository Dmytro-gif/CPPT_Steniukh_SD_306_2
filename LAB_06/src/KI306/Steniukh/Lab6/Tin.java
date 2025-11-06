package KI306.Steniukh.Lab6;

/**
 * Клас Tin — інша реалізація продукту: консерва.
 * Додає інформацію про бренд.
 */
public class Tin extends Product {
    private final String brand;

    /**
     * Конструктор консерви.
     *
     * @param name     назва вмісту
     * @param volumeMl об'єм у мл
     * @param priceUah ціна
     * @param brand    бренд
     */
    public Tin(String name, double volumeMl, double priceUah, String brand) {
        super(name, volumeMl, priceUah);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Tin: " + super.toString() + " [brand=" + brand + "]";
    }
}
