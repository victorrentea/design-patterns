package victor.training.oo.creational.singleton;

public class ServiceLocatorPlay {
    public static void main(String[] args) {

        // pana aici, nu s-a chemat inca constr Unica()
        System.out.println("----");
        Unica unica = Unica.getInstance();
        String config = unica.getConfig();
    }
}

class Unica {
    private static Unica INSTANCE/* = new Unica();*/;
    static private String config;

    private Unica() {
        System.out.println("NEW INSTANCE");
        config = "CEVA DE PE DISK CARE AM INCARCAT IN 20ms";
    }

    public static Unica getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Unica();
        }
        return INSTANCE;
    }

    public String getConfig() {
        return config;
    }
}