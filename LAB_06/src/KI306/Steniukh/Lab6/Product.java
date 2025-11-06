package KI306.Steniukh.Lab6;

/**
 * Абстрактний суперклас предметної області.
 * Містить загальні поля для продукту: назва, об'єм (в мл) та ціна.
 * Реалізує Comparable за полем price.
 *
 * @author Стенюх Дмитро
 * @version 1.0
 * @since 2025
 * 
 * Створити параметризований клас, що реалізує предметну область задану варіантом.
Клас має містити мінімум 4 методи опрацювання даних включаючи розміщення та
виймання елементів. Парні варіанти реалізують пошук мінімального елементу,
непарні – максимального. Написати на мові Java та налагодити програму-драйвер для
розробленого класу, яка мстить мінімум 2 різні класи екземпляри яких розмішуються у
9
екземплярі розробленого класу-контейнеру. Програма має розміщуватися в пакеті
Група.Прізвище.Lab6 та володіти коментарями, які дозволять автоматично
згенерувати документацію до розробленого пакету
 */
public abstract class Product implements Comparable<Product> {
    private final String name;
    private final double volumeMl;
    private final double priceUah;

    /**
     * Конструктор продукту.
     *
     * @param name     назва
     * @param volumeMl об'єм у мілілітрах
     * @param priceUah ціна у гривнях (або ін. одиницях)
     */
    public Product(String name, double volumeMl, double priceUah) {
        this.name = name;
        this.volumeMl = volumeMl;
        this.priceUah = priceUah;
    }

    public String getName() {
        return name;
    }

    public double getVolumeMl() {
        return volumeMl;
    }

    public double getPriceUah() {
        return priceUah;
    }

    @Override
    public int compareTo(Product other) {
        // Порівнюємо за ціною: менша ціна => "менший" елемент
        return Double.compare(this.priceUah, other.priceUah);
    }

    @Override
    public String toString() {
        return String.format("%s (vol=%.1fml, price=%.2f)", name, volumeMl, priceUah);
    }
}
