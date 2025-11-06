package KI306.Steniukh.Lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Параметризований контейнер для зберігання продуктів (Product або його нащадків).
 *
 * @param <T> тип елементів, які успадковують Product
 */
public class GenericContainer<T extends Product> {
    private final List<T> items;
    private final int capacity;

    /**
     * Конструктор контейнера з вказаною місткістю.
     *
     * @param capacity максимальна кількість елементів
     */
    public GenericContainer(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        this.capacity = capacity;
        this.items = new ArrayList<>(capacity);
    }

    /**
     * Додає елемент до контейнера (в кінець).
     *
     * @param item елемент
     * @return true якщо успішно додано, false якщо контейнер повний
     */
    public boolean add(T item) {
        if (isFull()) return false;
        return items.add(item);
    }

    /**
     * Видаляє елемент за індексом.
     *
     * @param index індекс (0..size-1)
     * @return видалений елемент
     * @throws IndexOutOfBoundsException якщо індекс некоректний
     */
    public T remove(int index) {
        return items.remove(index);
    }

    /**
     * Видаляє перше входження елемента (equals).
     *
     * @param item елемент
     * @return true якщо був видалений
     */
    public boolean remove(T item) {
        return items.remove(item);
    }

    /**
     * Повертає елемент за індексом.
     *
     * @param index індекс
     * @return елемент
     */
    public T get(int index) {
        return items.get(index);
    }

    /**
     * Повертає кількість елементів у контейнері.
     *
     * @return розмір
     */
    public int size() {
        return items.size();
    }

    /**
     * Повертає чи заповнений контейнер.
     */
    public boolean isFull() {
        return items.size() >= capacity;
    }

    /**
     * Очищає контейнер.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Повертає незмінюваний список усіх елементів.
     *
     * @return список елементів
     */
    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Знайти мінімальний елемент у контейнері (за Comparable).
     *
     * @return мінімальний елемент або null якщо контейнер порожній
     */
    public T findMin() {
        if (items.isEmpty()) return null;
        T min = items.get(0);
        for (T it : items) {
            if (it.compareTo(min) < 0) min = it;
        }
        return min;
    }

    /**
     *знайти максимальний елемент
     *
     * @return максимальний елемент або null якщо порожній
     */
    public T findMax() {
        if (items.isEmpty()) return null;
        T max = items.get(0);
        for (T it : items) {
            if (it.compareTo(max) > 0) max = it;
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GenericContainer(capacity=").append(capacity).append(", size=").append(size()).append(")\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("[%d] %s%n", i, items.get(i).toString()));
        }
        return sb.toString();
    }
}
