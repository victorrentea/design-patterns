package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class SpawningClassProxies {



    public static void main(String[] args) {

        Mate reala = new Mate();

        Callback callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("SRI: Invoca fraeru metoda " + method.getName()
                    + " cu parametrii " + Arrays.toString(args));
                return method.invoke(reala, args);
            }
        };
        Mate mate = (Mate) Enhancer.create(Mate.class, callback);

        biznislogic(mate);
    }

    private static void biznislogic(Mate mate) {
        System.out.println(mate.suma(1,1));
        System.out.println(mate.suma(1,2));
    }

}


class Mate {
    public Integer suma(int a, int b) {
        return a+b;
    }
}