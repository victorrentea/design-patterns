package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ZanaProxiurilor {
    public static void main(String[] args) {
        MateImpl piBune = new MateImpl();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("SRI: cumva o chemi pe " +
                        method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(piBune, args);
            }
        };
        IMate golem = (IMate) Proxy.newProxyInstance(
                ZanaProxiurilor.class.getClassLoader(),
                new Class<?>[]{IMate.class},h);


        System.out.println(golem.suma(1, 1));
        System.out.println(golem.suma(2, 0));
        System.out.println(golem.suma(-2, 4));
        System.out.println(golem.produs(2, 1));
        System.out.println(golem.produs(2, 2));
    }
}
interface IMate {
    int suma(int a, int b);
    int produs(int a, int b);
}

class MateImpl implements  IMate {

    @Override
    public int suma(int a, int b) {
        return a + b;
    }

    @Override
    public int produs(int a, int b) {
        return a*b;
    }
}