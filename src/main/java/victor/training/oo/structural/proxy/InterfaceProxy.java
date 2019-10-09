package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxy {
    public static void main(String[] args) {

        MateReal aiaPeBune = new MateReal();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("SRI: cumva suni metoda " + method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(aiaPeBune, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(
                InterfaceProxy.class.getClassLoader(),
                new Class<?>[]{Mate.class},
                h);

        // demo
        System.out.println(mate.sum(1,1));
        System.out.println(mate.sum(2,0));
        System.out.println(mate.sum(3,-1));
        System.out.println(mate.sum(3,1));
        System.out.println(mate.product(3,2));
    }
}


interface Mate {
    Integer sum(int a, int b);
    Integer product(int a, int b);
}

class MateReal implements Mate {

    @Override
    public Integer sum(int a, int b) {
        return a + b;
    }

    @Override
    public Integer product(int a, int b) {
        return a *b;
    }
}



