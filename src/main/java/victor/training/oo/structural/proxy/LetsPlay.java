package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LetsPlay {

    public static void main(String[] args) {
        MatematicaImpl realImpl = new MatematicaImpl();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("DS: Are you calling "  + method.getName() + " with args " + Arrays.toString(args));
                return method.invoke(realImpl, args);
            }
        };

        Matematika m = (Matematika) Proxy.newProxyInstance(LetsPlay.class.getClassLoader(),
                new Class<?>[]{Matematika.class},
                h);

        bizLogic(m);
    }

    private static void bizLogic(Matematika m) {
        System.out.println(m.suma(1,1));
        System.out.println(m.suma(2,0));
        System.out.println(m.suma(3,-1));
        System.out.println(m.suma(3,1));
        System.out.println(m.proizvedenie(3,2));
    }
}

interface Matematika {
    int suma(int a, int b);
    int proizvedenie(int a, int b);
}

class MatematicaImpl implements  Matematika {
    @Override
    public int suma(int a, int b) {
        return a+b;
    }

    @Override
    public int proizvedenie(int a, int b) {
        return a*b;
    }
}