package victor.training.oo.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class BabaDeLaParter {
    private final List<Barfitor> barfitori = new ArrayList<>();

    public static void main(String[] args) {
        BabaDeLaParter baba = new BabaDeLaParter();
        baba.attach(new Locatar1());
        baba.attach(new Locatar2());

        baba.lanseazaBarfa("A venit Rita cu un pletos");

    }

    private void lanseazaBarfa(String barfa) {
        for (Barfitor barfitor : barfitori) {
            barfitor.anunta(barfa);
        }
    }

    private void attach(Barfitor barfitor) {
        barfitori.add(barfitor);
    }
}
interface Barfitor {
    void anunta(String barfa);
}
class Locatar1 implements Barfitor {
    public void anunta(String barfa) {
        System.out.println("Locatar1 vorbeste despre " + barfa);
    }
}
class Locatar2 implements Barfitor {
    public void anunta(String barfa) {
        System.out.println("Locatar2 vorbeste despre " + barfa);
    }
}