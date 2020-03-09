package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
    public static void main(String[] args) {
        MateImpl mateImpl = new MateImpl();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Fraeru cheama functia " + method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
            new Class<?>[]{Mate.class},h);

        biznisLogic(mate);
    }

    private static void biznisLogic(Mate mate) {
        System.out.println("Oare cu cine vorbesc? " + mate.getClass());
        System.out.println(mate.suma(1, 1));
        System.out.println(mate.suma(2, 0));
        System.out.println(mate.suma(10, -8));
        System.out.println(mate.suma(10, 8));
        System.out.println(mate.produs(10, 8));
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