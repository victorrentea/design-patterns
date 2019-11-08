package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class IaUiteAicea {

    public static void main(String[] args) {
        Mate mate = new Mate();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Fraeru cheama metoda " + method.getName()
                     + " cu param " + Arrays.toString(args));
                return method.invoke(mate, args);
            }
        };
        IMate golem = (IMate) Proxy.newProxyInstance(IaUiteAicea.class.getClassLoader(),
                new Class<?>[]{IMate.class}, h);
        //demo

        System.out.println(golem.sum(1,1));
        System.out.println(golem.sum(2,0));
        System.out.println(golem.sum(3,-1));
        System.out.println(golem.sum(3,1));
        System.out.println(golem.prod(3,2));
    }
}

interface IMate {
    int sum(int a, int b);
    int prod(int a, int b);
}
class Mate implements IMate {
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int prod(int a, int b) {
        return a*b;
    }
}