package victor.training.oo.creational.singleton;

import victor.training.oo.stuff.ThreadUtils;

public class SingletonUPB {

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        B b1 = B.getInstance();
        B b2 = B.getInstance();
        long t1 = System.currentTimeMillis();
        System.out.println(b1.getCamp());
        System.out.println(b2.getCamp());
        System.out.println(t1 - t0);
    }
}


class B {
    private static B INSTANCE;
    private String camp;

    private B() {
        camp = porumb(); // scump, dicolo era mai ieftin
    }

    public String getCamp() {
        return camp;
    }

    private String porumb() {
        System.out.println("plantez");
        ThreadUtils.sleep(2000);
        return "stiulete";
    }

    public static B getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new B();
        }
        return INSTANCE;
    }
}