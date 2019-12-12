package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LetsPlay {
    public static void main(String[] args) {
        MathsImpl realImplem = new MathsImpl();
        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("SISMI: " +
                        method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(realImplem, args);
            }
        };
        MathsImpl maths = (MathsImpl) Enhancer.create(MathsImpl.class, interceptor);

        bizLogic(maths);
    }

    private static void bizLogic(MathsImpl maths) {
        System.out.println("Class : " + maths.getClass());
        System.out.println(maths.sum(1, 1));
        System.out.println(maths.sum(2, 0));
        System.out.println(maths.sum(3, -1));
        System.out.println(maths.sum(3, 1));
        System.out.println(maths.sqrt(4));
        System.out.println(maths.product(2, 1));
    }
}



class MathsImpl {

    public int sum(int a, int b) {
        return a +b;
    }

    public int product(int a, int b) {
        return a*b;
    }

    public int sqrt(int a) {
        return (int) Math.sqrt(a);
    }
}