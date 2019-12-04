package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
    public static void main(String[] args) {
        MateImpl realStuff = new MateImpl();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("SRI: O chemi pe "  +method.getName() +
                        " cu param " + Arrays.toString(args));
                return method.invoke(realStuff, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
                new Class<?>[]{Mate.class}, h);

        biznissLogic(mate);
    }

    private static void biznissLogic(Mate mate) {
        System.out.println(mate.getClass());
        System.out.println(mate.suma(1, 4));
        System.out.println(mate.suma(0, 5));
        System.out.println(mate.suma(-2, 7));
        System.out.println(mate.suma(2, 7));
        System.out.println(mate.produs(2, 7));
    }
}

interface Mate {
    int suma(int a, int b);
    int produs(int a, int b);
}

class MateImpl implements Mate {
    @Override
    public int suma(int a, int b) {
        return a + b;
    }

    @Override
    public int produs(int a, int b) {
        return a * b;
    }
}