package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxies {
    public static void main(String[] args) {
        MateImpl realStuff = new MateImpl();
//        Mate mate = (Mate) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
//                new Class<?>[]{Mate.class}, h);

        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("SRI: O chemi pe "  +method.getName() +
                        " cu param " + Arrays.toString(args));
                return method.invoke(realStuff, args);
            }
        };
        MateImpl mate = (MateImpl) Enhancer.create(MateImpl.class, methodInterceptor);

        biznissLogic(mate);
    }

    private static void biznissLogic(MateImpl mate) {
        System.out.println(mate.getClass());
        System.out.println(mate.suma(1, 4));
        System.out.println(mate.suma(0, 5));
        System.out.println(mate.suma(-2, 7));
        System.out.println(mate.suma(2, 7));
        System.out.println(mate.produs(2, 7));
    }
}


class MateImpl {
    public final int suma(int a, int b) {
        return a + b;
    }
    public final int produs(int a, int b) {
        return a * b;
    }
}