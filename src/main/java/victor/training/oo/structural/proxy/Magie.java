package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Magie {
    public static void main(String[] args) {

        MateImpl mateImpl = new MateImpl();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy,
                                 Method method,
                                 Object[] args) throws Throwable {
                System.out.println("Calls " +
                        method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };

        Mate mate = (Mate) Proxy.newProxyInstance(
                Magie.class.getClassLoader(),
                new Class<?>[]{Mate.class},
                h
        );

        businessLogic(mate);
    }

    private static void businessLogic(Mate mate) {
        System.out.println(mate.sum(1,1));
        System.out.println(mate.sum(0,2));
        System.out.println(mate.sum(1,2));
        System.out.println(mate.product(2,2));
        System.out.println(mate.sqrt(64));
    }
}


interface Mate {
    Integer sum(int a, int b);
    Integer product(int a, int b);
    Double sqrt(int a);
}

class MateImpl implements  Mate
{

    @Override
    public Integer sum(int a, int b) {
        return a + b;
    }

    @Override
    public Integer product(int a, int b) {
        return a * b;
    }

    @Override
    public Double sqrt(int a) {
        return Math.sqrt(a);
    }
}