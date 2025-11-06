package KI306.Steniukh.Lab3;


import java.io.IOException;

/**
 * MotorBoat — підклас Rowboat, що реалізує двигунні механізми.
 */
public class MotorBoat extends Rowboat implements Engine {
    private double fuelLiters;
    private double fuelConsumptionPerHour; // л/год
    private int rpm;
    private boolean engineOn;

    /**
     * Стандартний конструктор MotorBoat.
     *
     * @throws IOException якщо не вдається відкрити лог
     */
    public MotorBoat() throws IOException {
        super(new Hull("Алюміній", 6), null, null, "motorboat_log.txt");
        this.fuelLiters = 50.0;
        this.fuelConsumptionPerHour = 10.0;
        this.rpm = 0;
        this.engineOn = false;
        log("MotorBoat за замовчуванням створено");
    }

    /**
     * Повний конструктор MotorBoat.
     *
     * @param hull корпус
     * @param initialFuel початкове паливо
     * @throws IOException якщо не вдається відкрити лог
     */
    public MotorBoat(Hull hull, double initialFuel) throws IOException {
        super(hull, null, null, "motorboat_log.txt");
        this.fuelLiters = initialFuel;
        this.fuelConsumptionPerHour = 8.0;
        this.rpm = 0;
        this.engineOn = false;
        log("MotorBoat створено з hull=" + hull + " fuel=" + initialFuel);
    }

    /* -------------------- Реалізація Engine -------------------- */

    @Override
    public void startEngine() {
        if (engineOn) {
            log("startEngine: двигун вже запущено");
            return;
        }
        if (fuelLiters <= 0) {
            log("startEngine: немає пального");
            return;
        }
        engineOn = true;
        rpm = 800;
        log("Двигун запущено, rpm=" + rpm);
    }

    @Override
    public void stopEngine() {
        if (!engineOn) {
            log("stopEngine: двигун уже зупинений");
            return;
        }
        engineOn = false;
        rpm = 0;
        log("Двигун зупинено");
    }

    @Override
    public double getFuelLevel() {
        log("getFuelLevel -> " + fuelLiters);
        return fuelLiters;
    }

    @Override
    public void refuel(double amount) {
        if (amount <= 0) {
            log("refuel: неправильна кількість " + amount);
            return;
        }
        fuelLiters += amount;
        log("refuel: заправлено " + amount + "л, загалом=" + fuelLiters + "л");
    }

    /* -------------------- Додаткові методи MotorBoat -------------------- */

    /** Прискоритись (збільшити RPM і споживання) */
    public void accelerate(int deltaRpm) {
        if (!engineOn) {
            log("accelerate: двигун вимкнений");
            return;
        }
        rpm += deltaRpm;
        // змоделюємо витрату пального
        double hours = Math.abs(deltaRpm) / 1000.0; // умовна формула
        double consumed = fuelConsumptionPerHour * hours;
        fuelLiters = Math.max(0.0, fuelLiters - consumed);
        log("accelerate: +" + deltaRpm + " rpm, rpm=" + rpm + ", витрачено=" + consumed + "л, fuel=" + fuelLiters);
    }

    /** Зменшити швидкість */
    public void decelerate(int deltaRpm) {
        if (!engineOn) {
            log("decelerate: двигун вимкнений");
            return;
        }
        rpm = Math.max(0, rpm - deltaRpm);
        log("decelerate: -" + deltaRpm + " rpm, rpm=" + rpm);
    }

    /** Реалізація абстрактного методу propel */
    @Override
    public void propel(double power) {
        if (!engineOn) {
            log("propel: двигун вимкнений — рух неможливий");
            return;
        }
        // power — умовний множник (0..1)
        double hours = power * 0.1; // умовна модель
        double consumed = fuelConsumptionPerHour * hours;
        fuelLiters = Math.max(0.0, fuelLiters - consumed);
        log("propel: power=" + power + ", витрачено=" + consumed + "л, fuel=" + fuelLiters);
    }

    /** Екстрена зупинка (реалізація абстрактного методу) */
    @Override
    public void emergencyStop() {
        log("emergencyStop: виконано");
        engineOn = false;
        rpm = 0;
    }

    /** Повернути поточний RPM */
    public int getRPM() {
        log("getRPM -> " + rpm);
        return rpm;
    }

    /** Метод для діагностики (повертає коротку інфу) */
    public String diagnostics() {
        String d = "MotorBoat diagnostics: rpm=" + rpm + ", fuel=" + fuelLiters + ", oars=" + oars.size() + ", crew=" + crew.size();
        log("diagnostics: " + d);
        return d;
    }

    /** Коректне закриття логу — успадковано з Rowboat, можна доповнити */
    @Override
    public void close() {
        log("MotorBoat closing...");
        super.close();
    }
}
