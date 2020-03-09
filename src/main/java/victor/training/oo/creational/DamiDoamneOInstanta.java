package victor.training.oo.creational;

import java.util.Date;

public class DamiDoamneOInstanta {
    public static void main(String[] args) {
        ServiceLocator.getA().m();

//        A a = new A();
//        a.setB(new B());
//        a.m();

    }
    public void m() {
        ServiceLocator.getA().m();
    }
}
class ServiceLocator {

    public static B getB() {
        return new B();
    }
    public static A getA() {
        return new A();
    }

    public static C getC() {
        return new C();
    }
}


class A {
    private B b;
    private C c;

    public A() {
        this.b = ServiceLocator.getB();
        this.c = ServiceLocator.getC();
    }

//    public void setB(B b) {
//        this.b = b;
//    }

    public String m() {
        return b.metodaB().toUpperCase();
    }
}

class B {
    private A a;
    public B() {
        a = ServiceLocator.getA();
    }
    public String metodaB() {
        return "Hello world "+ new Date();
     }
}
class C{}