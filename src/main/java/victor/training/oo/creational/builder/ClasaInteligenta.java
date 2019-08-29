package victor.training.oo.creational.builder;

public class ClasaInteligenta {
    public static void main(String[] args) {
        new ClasaInteligenta()
                .setA("A")
                .setB("B");
    }

    private String a,b,c,d;

    public String getA() {
        return a;
    }

    public ClasaInteligenta setA(String a) {
        this.a = a;
        return this;
    }

    public ClasaInteligenta setB(String b) {
        this.b = b;
        return this;
    }
}
