package KI306.Steniukh.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактний суперклас Rowboat описує загальну логіку шлюпки/човна.
 * Містить три поля-об'єкти: Hull, список Oar, список CrewMember.
 *
 * @author Стенюх Дмитро
 * @version 1.1
 * @since 2025
 */
public abstract class Rowboat implements AutoCloseable {
    protected Hull hull;                 // об'єкт, що описує корпус
    protected List<Oar> oars;            // список весел
    protected List<CrewMember> crew;     // список членів екіпажу
    protected PrintWriter log;           // лог-файл для протоколу дій
    protected final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Повний конструктор.
     *
     * @param hull корпус
     * @param oars список весел
     * @param crew список екіпажу
     * @param logFilename ім'я файлу логу
     * @throws IOException у випадку проблем з відкриттям файлу
     */
    public Rowboat(Hull hull, List<Oar> oars, List<CrewMember> crew, String logFilename) throws IOException {
        this.hull = hull;
        this.oars = (oars != null) ? oars : new ArrayList<>();
        this.crew = (crew != null) ? crew : new ArrayList<>();
        this.log = new PrintWriter(new FileWriter(logFilename, true)); // append mode
        log("Rowboat створено: " + hull);
    }

    /**
     * Конструктор за замовчуванням із стандартним іменем логу "rowboat_log.txt".
     *
     * @throws IOException якщо файл не відкривається
     */
    public Rowboat() throws IOException {
        this(new Hull("Дерево", 4), new ArrayList<>(), new ArrayList<>(), "rowboat_log.txt");
    }

    /* -------------------- Методи, що логують дії -------------------- */

    /**
     * Записати рядок у лог з поточним часом.
     *
     * @param message текст дії
     */
    protected synchronized void log(String message) {
        String ts = LocalDateTime.now().format(dtf);
        log.println("[" + ts + "] " + message);
        log.flush();
    }

    /* -------------------- CRUD для весел і екіпажу -------------------- */

    /** Додає весло */
    public void addOar(Oar oar) {
        oars.add(oar);
        log("Додано весло: " + oar);
    }

    /** Видаляє останнє весло, якщо є */
    public Oar removeOar() {
        if (oars.isEmpty()) {
            log("Спроба removeOar: немає весел");
            return null;
        }
        Oar r = oars.remove(oars.size() - 1);
        log("Видалено весло: " + r);
        return r;
    }

    /** Додає члена екіпажу */
    public void addCrewMember(CrewMember member) {
        crew.add(member);
        log("Додано члена екіпажу: " + member);
    }

    /** Видаляє останнього члена екіпажу */
    public CrewMember removeCrewMember() {
        if (crew.isEmpty()) {
            log("Спроба removeCrewMember: екіпаж порожній");
            return null;
        }
        CrewMember r = crew.remove(crew.size() - 1);
        log("Видалено члена екіпажу: " + r);
        return r;
    }

    /* -------------------- Інформаційні методи -------------------- */

    /** Повертає опис шлюпки */
    public String getDescription() {
        String desc = "Hull: " + hull + "; oars=" + oars.size() + "; crew=" + crew.size();
        log("getDescription викликано: " + desc);
        return desc;
    }

    /** Повертає кількість весел */
    public int getOarCount() {
        log("getOarCount: " + oars.size());
        return oars.size();
    }

    /** Повертає кількість екіпажу */
    public int getCrewCount() {
        log("getCrewCount: " + crew.size());
        return crew.size();
    }

    /** Перевіряє готовність до руху (наприклад: має бути принаймні 1 весло і 1 член екіпажу) */
    public boolean isReady() {
        boolean ready = !oars.isEmpty() && !crew.isEmpty();
        log("isReady -> " + ready);
        return ready;
    }

    /* -------------------- Абстрактні методи для реалізації в підкласах -------------------- */

    /**
     * Основний метод пересування, реалізується у підкласі.
     *
     * @param power величина дії (залежить від підкласу)
     */
    public abstract void propel(double power);

    /**
     * Зупинити всі активності (реалізується у підкласі).
     */
    public abstract void emergencyStop();

    /* -------------------- Керування логом і закриття -------------------- */

    /**
     * Закриває ресурс логу; викликається явно або через AutoCloseable (try-with-resources).
     */
    @Override
    public void close() {
        log("Закриття Rowboat (close)");
        if (log != null) {
            log.close();
        }
    }

    /* -------------------- Допоміжні внутрішні класи (опис складових) -------------------- */

    /** Опис корпусу */
    public static class Hull {
        private String material;
        private int capacity;

        public Hull(String material, int capacity) {
            this.material = material;
            this.capacity = capacity;
        }

        public String getMaterial() { return material; }
        public int getCapacity() { return capacity; }

        @Override
        public String toString() {
            return material + " (capacity=" + capacity + ")";
        }
    }

    /** Весло */
    public static class Oar {
        private String material;
        public Oar(String material) { this.material = material; }
        @Override public String toString() { return "Oar{" + material + "}"; }
    }

    /** Член екіпажу */
    public static class CrewMember {
        private String name;
        private String role;
        public CrewMember(String name, String role) { this.name = name; this.role = role; }
        @Override public String toString() { return name + " (" + role + ")"; }
    }
}
