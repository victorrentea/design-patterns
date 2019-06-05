package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxy {
    interface Mathem {
        Integer sum(int a, int b);
        Integer prod(int a, int b);
    }
    static class RealMathem implements Mathem {

        @Override
        public Integer sum(int a, int b) {
            return a + b;
        }

        @Override
        public Integer prod(int a, int b) {
            return a*b;
        }
    }
    public static void main(String[] args) {
        RealMathem real = new RealMathem();
        InvocationHandler handler = (proxy, method, args1) -> {
            System.out.println("Cambridge Analytica: you " +
                    "are trying to call: " +
                    method.getName() +
                    " with args "+ Arrays.toString(args1));

            return method.invoke(real, args1);
        };
        Mathem orcLOTR = (Mathem) Proxy.newProxyInstance(
                InterfaceProxy.class.getClassLoader(),
                new Class<?>[]{Mathem.class},
                handler
        );

        System.out.println(orcLOTR.sum(1,1));
        System.out.println(orcLOTR.sum(2,1));
        System.out.println(orcLOTR.prod(2,1));
    }
}
