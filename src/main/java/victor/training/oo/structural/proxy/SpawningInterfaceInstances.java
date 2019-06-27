package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class SpawningInterfaceInstances {



    public static void main(String[] args) {

        MateImpl reala = new MateImpl();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Invoca fraeru metoda " + method.getName()
                    + " cu parametrii " + Arrays.toString(args));
                return method.invoke(reala, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(SpawningInterfaceInstances.class.getClassLoader(),
                new Class<?>[]{Mate.class},
                h);

        biznislogic(mate);
    }

    private static void biznislogic(Mate mate) {
        System.out.println(mate.suma(1,1));
        System.out.println(mate.suma(1,2));
    }

}

interface Mate {
    Integer suma(int a, int b);
}

class MateImpl implements  Mate {
    @Override
    public Integer suma(int a, int b) {
        return a+b;
    }
}