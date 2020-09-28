package victor.training.oo.creational.singleton;

public class ServiceLocatorPlay {
    public static void main(String[] args) {

        // from test code
//        ServiceLocator.setTestImplem(B.class, mockB);
    }
}

class A {
    private final B b;

    public A(B b) {
        this.b = b;
    }

    public void m() {
//        B b = ServiceLocator.getObject(B.class);
        String c = b.getConfig();

    }
}
class B {
    Repo c = new Repo();
    public B() {
    }

    public String getConfig() {
        return null;
    }
}
class Repo {


}