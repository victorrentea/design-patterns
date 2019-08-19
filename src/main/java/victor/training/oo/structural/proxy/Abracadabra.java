package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Abracadabra {
    public static void main(String[] args) {

        Mathematics realStuff = new Mathematics();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("MI5 here: the lad calling "+
                        method.getName()+" with params " +
                        Arrays.toString(args));
                return method.invoke(realStuff, args);
            }
        };
        IMathematics golem = (IMathematics) Proxy.newProxyInstance(
                Abracadabra.class.getClassLoader(),
                new Class<?>[] {IMathematics.class},
                h
        );

        System.out.println(golem.sum(1,1));
        System.out.println(golem.sum(0,2));
        System.out.println(golem.sum(1,2));
        System.out.println(golem.product(1,2));
    }
}
