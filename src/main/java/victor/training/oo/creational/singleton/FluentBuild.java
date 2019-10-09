package victor.training.oo.creational.singleton;

public class FluentBuild {
    public static void main(String[] args) {
        new A()
                .setA("a")
                .setB("b");
    }
}


class A {
    private String a,b,c,d;

    public String getA() {
        return a;
    }

    public A setA(String a) {
        this.a = a;
        return this;
    }

    public String getB() {
        return b;
    }

    public A setB(String b) {
        this.b = b;
        return this;
    }

    public String getC() {
        return c;
    }

    public A setC(String c) {
        this.c = c;
        return this;
    }

    public String getD() {
        return d;
    }

    public A setD(String d) {
        this.d = d;
        return this;
    }
}