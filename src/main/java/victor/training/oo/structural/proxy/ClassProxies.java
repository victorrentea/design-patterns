package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ClassProxies {


    public static void main(String[] args) {
        MateReala2 realImplem = new MateReala2();

        MethodInterceptor h = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println("Ma cheama fraeru metoda: " +
                        method.getName() + " si param: " + Arrays.toString(args));
                return method.invoke(realImplem, objects);
            }
        };
        MateReala2 mateDinNimic = (MateReala2) Enhancer.create(MateReala2.class, h);

        System.out.println("Oare cine este langa mine ? " + mateDinNimic.getClass().getName());
        System.out.println(mateDinNimic.sum(1,1));
        System.out.println(mateDinNimic.sum(1,2));
        System.out.println(mateDinNimic.toString(3));
    }
}

class MateReala2 {

    public Integer sum(int a, int b) {
        return a + b;
    }

    public String toString(int nh) {
        return nh + "";
    }
}