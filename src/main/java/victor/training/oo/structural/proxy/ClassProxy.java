package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ClassProxy {

    public static ExpensiveOps proxy(ExpensiveOps realImplem) {
        Callback callback = new MethodInterceptor() {

            private Map<List<Object>, Object> cache = new ConcurrentHashMap<>(); // Ваше OutOfMemoryError, сэр!

            private List<Object> getCacheKey(Method method, Object... args) {
                List<Object> list = new ArrayList<Object>();
                list.add(method.getName());
                list.addAll(Arrays.asList(args));
                return list;
            }

            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                List<Object> key = getCacheKey(method, args);
                log.debug("Intercepted: " + method.getName());
                return cache.computeIfAbsent(key,
                        Unchecked.function(unused -> method.invoke(realImplem, args)));

            }
        };
        return (ExpensiveOps) Enhancer.create(ExpensiveOps.class, callback);
    }
}
