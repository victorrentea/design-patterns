package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class InterfaceProxies {
    public static void main(String[] args) {
        Mate mateImpl = new Mate();
        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.debug("SRI: Invoca fraeru metoda {}  cu param {}", method.getName(), Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };
        Mate mate = (Mate) Enhancer.create(Mate.class, interceptor);

        bizniss(mate);
    }

    private static void bizniss(Mate mate) {
        System.out.println("Io cu cine puii mei vorbesc ? " + mate.getClass());
        System.out.println(mate.suma(1, 1));
        System.out.println(mate.suma(2, 0));
        System.out.println(mate.suma(3, -1));
        System.out.println(mate.suma(3, 1));
        System.out.println(mate.product(3, 2));
    }
}


class Mate{
    public int suma(int a, int b) {
        return a + b;
    }

    public int product(int a, int b) {
        return a * b;
    }
}