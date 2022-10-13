package victor.training.patterns.builder;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Player {
    String a;
    String b;
    String firstName;
    String lastName;

    Player withName(String firstName, String lastName) {
        return new Player(a, b, firstName, lastName);
    }
}


class Singleton {

    private static Singleton INSTANCE;

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }

    private Singleton() {
    }
    public void vocalize() {
        // chestii
    }
}
class OClasaNormala {
    public void vocalize() {

    }
}


class Singer {
    private final OClasaNormala dependinta; // #3 DI

    public Singer(OClasaNormala dependinta) {
        this.dependinta = dependinta;
    }

    public void method() {
//        Singleton.getInstance().vocalize(); // #1 Singleton
//        new OClasaNormala(aia, new Aialalta(new Tasu)).vocalize(); // #2 new rau:
//        dependinta.voczalize();
        // logica de testat.
    }
}


class Client {
    public static void main(String[] args) {
//       Singleton.INSTANCE.vocalize();
    }
}