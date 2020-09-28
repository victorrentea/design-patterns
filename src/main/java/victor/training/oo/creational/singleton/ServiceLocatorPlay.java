package victor.training.oo.creational.singleton;

public class ServiceLocatorPlay {
    public static void main(String[] args) {

        // pana aici, nu s-a chemat inca constr Unica()
        System.out.println("----");
        Unica unica = Unica.getInstance();
    }
}

class Unica {
    private static Unica INSTANCE;

    private Unica() {
        System.out.println("NEW INSTANCE");
    }

    public static Unica getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Unica();
        }
        return INSTANCE;
    }
}