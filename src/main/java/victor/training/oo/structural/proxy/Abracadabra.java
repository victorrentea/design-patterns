package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Abracadabra {
    public static void main(String[] args) {

        Mathematics realStuff = new Mathematics();

        MethodInterceptor callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("MI5 here: the lad calling "+
                        method.getName()+" with params " +
                        Arrays.toString(args));
                return method.invoke(realStuff, args);
            }
        };
        Mathematics golem = (Mathematics) Enhancer.create(Mathematics.class, callback);

        System.out.println("Ma', who are you ?!" + golem.getClass());
        System.out.println(golem.sum(1,1));
        System.out.println(golem.sum(0,2));
        System.out.println(golem.sum(1,2));
        System.out.println(golem.product(1,2));
    }
}
