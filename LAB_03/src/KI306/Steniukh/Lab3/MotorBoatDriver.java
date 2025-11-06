package KI306.Steniukh.Lab3;


import java.io.IOException;
/**
 * Клас-драйвер для демонстрації роботи {@link MotorBoat}.
 * Виконує приклад створення моторного човна, екіпажу і весел,
 * а також моделює запуск двигуна, рух і заправку.
 *
 * @author Стенюх Дмитро
 * @version 1.0
 * @since 2025
 */
public class MotorBoatDriver {
    public static void main(String[] args) {
        
        try (MotorBoat boat = new MotorBoat(new Rowboat.Hull("Алюміній", 8), 60.0)) {
            // додаємо складові
            boat.addOar(new Rowboat.Oar("Дерево"));
            boat.addOar(new Rowboat.Oar("Дерево"));
            boat.addCrewMember(new Rowboat.CrewMember("Іван", "Капітан"));
            boat.addCrewMember(new Rowboat.CrewMember("Петро", "Матрос"));

            System.out.println(boat.getDescription());
            System.out.println(boat.diagnostics());

            // Стартуємо двигун і виконуємо ряд дій
            boat.startEngine();
            boat.accelerate(400);   // збільшили rpm
            boat.propel(0.5);       // рух з певною потужністю
            boat.decelerate(200);
            boat.stopEngine();

            // Заправка, ще одна спроба
            boat.refuel(20.0);
            boat.startEngine();
            boat.propel(0.2);
            boat.emergencyStop();

            // get info
            System.out.println("Fuel left: " + boat.getFuelLevel() + " л");
            System.out.println("RPM: " + boat.getRPM());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
