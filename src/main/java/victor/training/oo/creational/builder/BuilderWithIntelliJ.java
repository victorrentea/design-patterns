package victor.training.oo.creational.builder;

public class BuilderWithIntelliJ {


    public static void main(String[] args) {
        AClass a = new AClass()
                .setA("a")
                .setB("b")
                .setZ(new ZClass()
                        .setA("zA"));


        a.setA("anotherA");
    }
}

class ZClass {
    private String a;

    public String getA() {
        return a;
    }

    public ZClass setA(String a) {
        this.a = a;
        return this;
    }
}


class AClass {
    private String a,b,c,d;
    private int e;
    private ZClass z;

    public ZClass getZ() {
        return z;
    }

    public AClass setZ(ZClass z) {
        this.z = z;
        return this;
    }

    public String getA() {
        return a;
    }

    public AClass setA(String a) {
        this.a = a;
        return this;
    }

    public String getB() {
        return b;
    }

    public AClass setB(String b) {
        this.b = b;
        return this;
    }

    public String getC() {
        return c;
    }

    public AClass setC(String c) {
        this.c = c;
        return this;
    }

    public String getD() {
        return d;
    }

    public AClass setD(String d) {
        this.d = d;
        return this;
    }

    public int getE() {
        return e;
    }

    public AClass setE(int e) {
        this.e = e;
        return this;
    }
}
