package victor.training.patterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyIntro {
    private static final Logger log = LoggerFactory.getLogger(ProxyIntro.class);
    public static void main(String[] args) {
        // pretend to BE Spring here
        Maths realMaths = new Maths();
        Callback h = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.debug("calling " + method.getName() + " with args " + Arrays.toString(args));
                return method.invoke(realMaths, args);
            }
        };
        Maths mathProxy = (Maths) Enhancer.create(Maths.class, h);
        SecondGrade secondGrade = new SecondGrade(mathProxy);



        //@GetMapping
        secondGrade.mathClass();
    }

}
// @Service
class SecondGrade {
    private final Maths maths;

    SecondGrade(Maths maths) {
        this.maths = maths;
    }

    public void mathClass() {
        System.out.println("WHO ARE YOU!??! " + maths.getClass());
        System.out.println(maths.sum(2, 4));
        System.out.println(maths.sum(1, 5));
        System.out.println(maths.product(2, 3));
    }
}
//@Component @Aspect
//@Service
class Maths {
//    @Logged @Transactional @Timed //
    public int sum(int a, int b) {
        return a + b;
    }

    public int product(int a, int b) {
        return a * b;
    }
}


// Key Points
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
