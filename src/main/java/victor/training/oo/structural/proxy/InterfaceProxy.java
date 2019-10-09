package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxy {
    public static void main(String[] args) {

        MateReal aiaPeBune = new MateReal();

        MethodInterceptor callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("SRI: cumva suni metoda " + method.getName() + " cu param " + Arrays.toString(args));
                return method.invoke(aiaPeBune, args);
            }
        };
        MateReal mate = (MateReal) Enhancer.create(MateReal.class, callback);

        // demo
        System.out.println("Oare cu cine vorbesc ? " + mate.getClass());
        System.out.println(mate.sum(1,1));
        System.out.println(mate.sum(2,0));
        System.out.println(mate.sum(3,-1));
        System.out.println(mate.sum(3,1));
        System.out.println(mate.product(3,2));
    }
}



class MateReal  {
    public Integer sum(int a, int b) {
        return a + b;
    }
    public Integer product(int a, int b) {
        return a *b;
    }
}



