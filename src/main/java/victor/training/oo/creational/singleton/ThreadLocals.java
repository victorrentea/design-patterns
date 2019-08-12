package victor.training.oo.creational.singleton;

import javax.xml.transform.sax.SAXSource;

public class ThreadLocals {

    static ThreadLocal<String> userNameTLS= new ThreadLocal<>();

    public static void main(String[] args) {
        String username = "jdoe";
        userNameTLS.set(username);
        try {
            new Service1().m();
        } finally {
            userNameTLS.remove();
        }
    }
}

class Service1 {
    Service2 s2 = new Service2();
    void m() {
        s2.m();
    }

}
class Service2 {
    Repo repo = new Repo();
    void m() {
        repo.m();
//        chemAltServiciuHttp({header:ThreadLocals.userNameTLS.get()} )
    }
}
class Repo {
    public void m() {
        String username = ThreadLocals.userNameTLS.get();
        System.out.println("Userul " + username + " calls here");
    }
}

class X {
    private String a,b,c,d;

    public X setA(String a) {
        this.a = a;
        return this;
    }

    public X setB(String b) {
        this.b = b;
        return this;
    }

    public X setC(String c) {
        this.c = c;
        return this;
    }

    public X setD(String d) {
        this.d = d;
        return this;
    }

    {
        new X().setA("a")
                .setB("b");
    }
}