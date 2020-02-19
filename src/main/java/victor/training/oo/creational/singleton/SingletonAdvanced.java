package victor.training.oo.creational.singleton;

public class SingletonAdvanced {
    public static void main(String[] args) {
        new CevaMare(/*new A(), new B(), null, null*/);
        m(1, 1, "a");
    }

    public static void m(int a, int g, String b) {

    }
}

class CevaMare {
    private A a;
    private B b;
    private D c;
    private D d;

//    CevaMare(A a, B b, D c, D d) {
////        this.a = a;
////        this.b = b;
////        this.c = c;
////        this.d = d;
////    }

    public void setA(A a) {
        this.a = a;
    }

    public void setB(B b) {
        this.b = b;
    }

    public void setC(D c) {
        this.c = c;
    }

    public void setD(D d) {
        this.d = d;
    }

    public void m() {
        a.toString();
        b.toString();
    }
}
class A{}
class B{}
class C{}
class D{}