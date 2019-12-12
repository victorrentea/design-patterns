package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LetsPlay {
    public static void main(String[] args) {
        MathsImpl realImplem = new MathsImpl();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("SISMI: " +
                        method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(realImplem, args);
            }
        };
        Maths maths = (Maths) Proxy.newProxyInstance(LetsPlay.class.getClassLoader(),
                new Class<?>[]{Maths.class}, h);

        bizLogic(maths);
    }

    private static void bizLogic(Maths maths) {
        System.out.println(maths.sum(1, 1));
        System.out.println(maths.sum(2, 0));
        System.out.println(maths.sum(3, -1));
        System.out.println(maths.sum(3, 1));
        System.out.println(maths.sqrt(4));
        System.out.println(maths.product(2, 1));
    }
}


interface Maths {
    int sum(int a, int b);
    int product(int a, int b);
    int sqrt(int a);
}

class MathsImpl implements Maths {

    @Override
    public int sum(int a, int b) {
        return a +b;
    }

    @Override
    public int product(int a, int b) {
        return a*b;
    }

    @Override
    public int sqrt(int a) {
        return (int) Math.sqrt(a);
    }
}