package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.security.krb5.Realm;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxy {

    public static void main(String[] args) {
        InterfaceProxy.RealMathem real = new InterfaceProxy.RealMathem();

        InterfaceProxy.RealMathem mathem =
                (InterfaceProxy.RealMathem) Enhancer.create(InterfaceProxy.RealMathem.class,
                        (MethodInterceptor) (o, method, args1, methodProxy) -> {
                            System.out.println("Cambridge Analytica: you " +
                                    "are trying to call: " +
                                    method.getName() +
                                    " with args "+ Arrays.toString(args1));

                            return method.invoke(real, args1);
                        });
//                new InterfaceProxy.RealMathem() {
//                    @Override
//                    public Integer prod(int a, int b) {
//                        System.out.println("Cambridge Analytica: you " +
//                                    "are trying to call: " +
//                                    "prod" +
//                                    " with args "+ a +","+ b);
//                        return real.prod(a, b);
//                    }
//                };

        System.out.println(mathem.getClass());
        System.out.println(mathem.prod(3,2));
    }
}
