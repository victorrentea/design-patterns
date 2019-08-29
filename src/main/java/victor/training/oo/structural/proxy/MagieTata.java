package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class MagieTata {
    public static void main(String[] args) {
        MateReal real = new MateReal();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("Calls method {}, with params {}",
                        method.getName(),
                        Arrays.toString(args));
                return method.invoke(real, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(MagieTata.class.getClassLoader(),
                new Class<?>[]{Mate.class},
                h);

        System.out.println(mate.sum(1,1));
        System.out.println(mate.sum(-2,4));
        System.out.println(mate.sum(1,4));
    }
}
