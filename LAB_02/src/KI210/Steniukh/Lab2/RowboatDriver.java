package KI210.Steniukh.Lab2;

import java.io.IOException;


public class RowboatDriver {
    public static void main(String[] args) {
        try {
            Rowboat boat = new Rowboat(new Hull("Метал", 6));

            boat.addOar(new Oar("Дерево"));
            boat.addOar(new Oar("Карбон"));

            boat.addCrewMember(new CrewMember("Іван", "Керманич"));
            boat.addCrewMember(new CrewMember("Петро", "Весляр"));

            System.out.println(boat.getDescription());

            boat.startRowing();
            Thread.sleep(1000);
            boat.stopRowing();

            boat.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
