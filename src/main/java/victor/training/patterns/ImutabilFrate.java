package victor.training.patterns;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImutabilFrate {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("unu");
        Immutable immutable = new Immutable(list);
        // de aici in jos unui obiect imutabil nu mai poti sa-i schimbi starea
        inocenta(immutable);

        System.out.println("La final: " + immutable.getStrings());
    }

    private static void inocenta(Immutable immutable) {
        // totusi pot schimba starea
        immutable.getStrings().add("doi"); // nu cumva-l insel?
        // e posibil ca cine a scris codul asta sa creada ca a modificat parametrul.

        // SCOP: sa explodeze client
    }
}


//class Square { // mai bine COMPOZITIE (camp pointer la alta instnta) decat MOSTENIRE
//    private Rectangle r;
//
//    public void setEdge(int edge) {
//        r.setWidth(edge);
//        r.setHeight(edge);
//    }
//
//    public int  getArea() {
//        return r.getArea();
//    }
//}


class Immutable {
    private final List<String> strings;

    Immutable(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
//        return new ArrayList<>(strings); // - malloc ==> GC :(

//        return new Collections.UnmodifiableList<>(strings); // JDK nu-ti da voie sa vezi clasa UnmodifiableList

        // ci in schimb te obliga sa obtii instanta printr-un apel de "Static Factory Method" (TM)
         // folosit aici pentru a permite librariei sa-ti dea un tip pe care tu sa nu-l vezi. (polimorfism)
        return Collections.unmodifiableList(strings);
        // + nu aloci decat cativa octeti + crapi cu exceptie in client cand face add/remove
    }
}