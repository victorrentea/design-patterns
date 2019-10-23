package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
    public static void main(String[] args) {

        MathsReal mathsReal = new MathsReal();

        MethodInterceptor handler = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("MI5 here: " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(mathsReal, args);
            }
        };
        MathsReal golem = (MathsReal) Enhancer.create(MathsReal.class, handler);

        System.out.println(golem.sum(1,1));
        System.out.println(golem.sum(2,0));
        System.out.println(golem.sum(3,-1));
        System.out.println(golem.sum(3,1));
        System.out.println(golem.prod(2,1));
    }
}


class MathsReal {
    public Integer sum(int a, int b) {
        return a+b;
    }
    public Integer prod(int a, int b) {
        return a*b;
    }
}