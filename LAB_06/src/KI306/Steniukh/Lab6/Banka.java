package KI306.Steniukh.Lab6;

/**
 * Клас Banka — конкретна реалізація продукту: банка.
 * Додає інформацію про матеріал кришки.
 */
public class Banka extends Product {
    public final String lidMaterial;

    /**
     * Конструктор банки.
     *
     * @param name        назва вмісту (наприклад "Горох")
     * @param volumeMl    об'єм у мл
     * @param priceUah    ціна
     * @param lidMaterial матеріал кришки
     */
    public Banka(String name, double volumeMl, double priceUah, String lidMaterial) {
        super(name, volumeMl, priceUah);
        this.lidMaterial = lidMaterial;
    }

    public String getLidMaterial() {
        return lidMaterial;
    }

    @Override
    public String toString() {
        return "Banka: " + super.toString() + " [lid=" + lidMaterial + "]";
    }
}
