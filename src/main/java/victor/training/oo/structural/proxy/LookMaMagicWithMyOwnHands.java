//package victor.training.oo.structural.proxy;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class LookMaMagicWithMyOwnHands {
//
//    public static void main(String[] args) {
//        ExpensiveOps realImplem = new ExpensiveOps();
//        InvocationHandler h = new InvocationHandler() {
//            private Map<Object, Object> cache = new HashMap<>();
//
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                log.debug("CIA here: you are calling {} with args {}", method.getName(), Arrays.toString(args));
//
//                return cache.computeIfAbsent(args[0], nn -> {
//                    try {
//                        return method.invoke(realImplem, args);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//            }
//        };
//        IExpensiveOps magic = (IExpensiveOps) Proxy.newProxyInstance(
//                LookMaMagicWithMyOwnHands.class.getClassLoader(),
//                new Class<?>[]{IExpensiveOps.class},
//                h);
//
//        System.out.println(magic.isPrime(2));
//        System.out.println(magic.isPrime(3));
//        System.out.println(magic.isPrime(4));
//    }
//}
