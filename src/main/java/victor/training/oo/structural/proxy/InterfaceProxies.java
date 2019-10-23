package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
    public static void main(String[] args) {

        MathsReal mathsReal = new MathsReal();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("MI5 here: " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(mathsReal, args);
            }
        };


        Maths golem = (Maths) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
                new Class<?>[]{ Maths.class},
                h);

        System.out.println(golem.sum(1,1));
        System.out.println(golem.sum(2,0));
        System.out.println(golem.sum(3,-1));
        System.out.println(golem.sum(3,1));
        System.out.println(golem.prod(2,1));
    }
}


interface Maths {
    Integer sum(int a, int b);
    Integer prod(int a, int b);
}

class MathsReal implements  Maths{

    @Override
    public Integer sum(int a, int b) {
        return a+b;
    }

    @Override
    public Integer prod(int a, int b) {
        return a*b;
    }
}