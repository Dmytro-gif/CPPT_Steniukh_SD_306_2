package KI306.Steniukh.Lab3;



/**
 * Інтерфейс Engine — описує базову взаємодію з двигуном.
 */
public interface Engine {
    /** Запустити двигун */
    void startEngine();

    /** Зупинити двигун */
    void stopEngine();

    /** Отримати поточний рівень пального (літри) */
    double getFuelLevel();

    /** Заправити на amount літрів */
    void refuel(double amount);
}
