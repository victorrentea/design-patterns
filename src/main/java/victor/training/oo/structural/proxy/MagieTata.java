package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class MagieTata {
    public static void main(String[] args) {
        MateReal real = new MateReal();

        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.info("Calls method {}, with params {}",
                        method.getName(),
                        Arrays.toString(args));
                return method.invoke(real, args);
            }
        };
        MateReal mate = (MateReal) Enhancer.create(MateReal.class, methodInterceptor);

        System.out.println("Tu cine esti frate ?! " + mate.getClass());
        System.out.println(mate.sum(1,1));
        System.out.println(mate.sum(-2,4));
        System.out.println(mate.sum(1,4));
    }
}
