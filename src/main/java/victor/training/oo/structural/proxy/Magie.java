package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Magie {
    public static void main(String[] args) {

        MateImpl mateImpl = new MateImpl();

//        InvocationHandler h = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy,
//                                 Method method,
//                                 Object[] args) throws Throwable {
//                System.out.println("Calls " +
//                        method.getName() + " cu param " + Arrays.toString(args));
//                return method.invoke(mateImpl, args);
//            }
//        };

        MethodInterceptor callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("Calls " +
                        method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };
        MateImpl mate = (MateImpl) Enhancer.create(MateImpl.class,
            callback);
//        MateImpl mate = (Mate) Proxy.newProxyInstance(
//                Magie.class.getClassLoader(),
//                new Class<?>[]{Mate.class},
//                h
//        );

        businessLogic(mate);
    }

    private static void businessLogic(MateImpl mate) {
        System.out.println("Oare cu cine vorbesc ? " + mate.getClass());
        System.out.println(mate.sum(1,1));
        System.out.println(mate.sum(0,2));
        System.out.println(mate.sum(1,2));
        System.out.println(mate.product(2,2));
        System.out.println(mate.sqrt(64));
    }
}



class MateImpl {
    public Integer sum(int a, int b) {
        return a + b;
    }
    public Integer product(int a, int b) {
        return a * b;
    }
    public Double sqrt(int a) {
        return Math.sqrt(a);
    }
}