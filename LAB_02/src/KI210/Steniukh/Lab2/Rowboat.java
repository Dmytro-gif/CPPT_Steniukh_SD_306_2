package KI210.Steniukh.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас Rowboat описує шлюпку на веслах.
 * Вона має корпус, набір весел та екіпаж.
 *
 * @author Стєнюх Дмитро
 * @version 1.0
 * @since 2025
 */

public class Rowboat {
    private Hull hull;
    private List<Oar> oars;
    private List<CrewMember> crew;
    private PrintWriter log;

 
    public Rowboat() throws IOException {
        this(new Hull("Дерево", 4), new ArrayList<>(), new ArrayList<>());
    }

    
    public Rowboat(Hull hull) throws IOException {
        this(hull, new ArrayList<>(), new ArrayList<>());
    }

  
    public Rowboat(Hull hull, List<Oar> oars, List<CrewMember> crew) throws IOException {
        this.hull = hull;
        this.oars = oars;
        this.crew = crew;
        this.log = new PrintWriter(new FileWriter("rowboat_log.txt", true)); 
        log("Створено нову шлюпку");
    }

    public void addOar(Oar oar) {
        oars.add(oar);
        log("Додано весло: " + oar);
    }

    public void addCrewMember(CrewMember member) {
        crew.add(member);
        log("Додано члена екіпажу: " + member);
    }

    public void startRowing() {
        log("Гребля розпочата. Весел: " + oars.size() + ", екіпаж: " + crew.size());
    }

    public void stopRowing() {
        log("Гребля зупинена.");
    }

    public int getOarCount() { return oars.size(); }
    public int getCrewCount() { return crew.size(); }

    public String getDescription() {
        return "Шлюпка з корпусом " + hull + ", кількість весел: " + getOarCount() +
                ", екіпаж: " + getCrewCount();
    }

    private void log(String message) {
        log.println(message);
        log.flush();
    }

   
    
    public void removeOar() {
        if (!oars.isEmpty()) {
            Oar oar = oars.remove(oars.size() - 1);
            log("Видалено весло: " + oar);
        }
    }

    
    public void removeCrewMember() {
        if (!crew.isEmpty()) {
            CrewMember member = crew.remove(crew.size() - 1);
            log("Видалено члена екіпажу: " + member);
        }
    }

   
    public boolean isReady() {
        boolean ready = !oars.isEmpty() && !crew.isEmpty();
        log("Перевірка готовності: " + ready);
        return ready;
    }

    public void close() {
        log("Закриття файлу логів.");
        log.close();
    }
}


class Hull {
    private String material;
    private int capacity;
    public Hull(String material, int capacity) { this.material = material; this.capacity = capacity; }
    @Override public String toString() { return material + ", місткість " + capacity + " осіб"; }
}


class Oar {
    private String material;
    public Oar(String material) { this.material = material; }
    @Override public String toString() { return "Весло з " + material; }
}


class CrewMember {
    private String name;
    private String role;
    public CrewMember(String name, String role) { this.name = name; this.role = role; }
    @Override public String toString() { return name + " (" + role + ")"; }
}
