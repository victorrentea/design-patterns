package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class IaUiteAicea {

    public static void main(String[] args) {
        Mate mate = new Mate();
        MethodInterceptor callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("Fraeru cheama metoda " + method.getName()
                     + " cu param " + Arrays.toString(args));
                return method.invoke(mate, args);
            }
        };
        Mate golem = (Mate) Enhancer.create(Mate.class, callback);
        //demo

        System.out.println(golem.sum(1,1));
        System.out.println(golem.sum(2,0));
        System.out.println(golem.sum(3,-1));
        System.out.println(golem.sum(3,1));
        System.out.println(golem.prod(3,2));
    }
}
class Mate {
    public int sum(int a, int b) {
        return a + b;
    }
    public int prod(int a, int b) {
        return a*b;
    }
}