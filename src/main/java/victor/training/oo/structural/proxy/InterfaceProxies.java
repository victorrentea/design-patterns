package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {


    public static void main(String[] args) {
        MateReala realImplem = new MateReala();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Ma cheama fraeru metoda: " +
                        method.getName() + " si param: " + Arrays.toString(args));
                return method.invoke(realImplem, args);
            }
        };


        Mate mateDinNimic = (Mate) Proxy.newProxyInstance(
                InterfaceProxies.class.getClassLoader(),
                new Class<?>[]{Mate.class},
                h);

        System.out.println("Oare cine este langa mine ? " + mateDinNimic.getClass().getName());
        System.out.println(mateDinNimic.sum(1,1));
        System.out.println(mateDinNimic.sum(1,2));
        System.out.println(mateDinNimic.toString(3));
    }
}

interface Mate {
    Integer sum(int a, int b);
    String toString(int nh);
}

class MateReala implements Mate {

    @Override
    public Integer sum(int a, int b) {
        return a + b;
    }

    @Override
    public String toString(int nh) {
        return nh + "";
    }
}