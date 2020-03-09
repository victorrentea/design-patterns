package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxies {
    public static void main(String[] args) {
        Mate mateImpl = new Mate();

        Callback callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("Fraeru cheama functia " + method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };
        Mate mate = (Mate) Enhancer.create(Mate.class, callback);

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

class Mate  {
    public int suma(int a, int b) {
        return a + b;
    }
    public int produs(int a, int b) {
        return a * b;
    }
}