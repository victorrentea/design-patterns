package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class InterfaceProxies {
    public static void main(String[] args) {
        MateImpl mateImpl = new MateImpl();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.debug("SRI: Invoca fraeru metoda {}  cu param {}", method.getName(), Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(Mate.class.getClassLoader(),
                new Class<?>[]{Mate.class}, h);

        bizniss(mate);
    }

    private static void bizniss(Mate mate) {
        System.out.println(mate.suma(1, 1));
        System.out.println(mate.suma(2, 0));
        System.out.println(mate.suma(3, -1));
        System.out.println(mate.suma(3, 1));
        System.out.println(mate.product(3, 2));
    }
}

interface Mate {
    int suma(int a, int b);
    int product(int a, int b);
}

class MateImpl implements Mate {
    @Override
    public int suma(int a, int b) {
        return a + b;
    }

    @Override
    public int product(int a, int b) {
        return a * b;
    }
}