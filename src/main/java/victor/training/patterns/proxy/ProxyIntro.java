package victor.training.patterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

//@SpringBootApplication

public class ProxyIntro {
    private static final Logger log = LoggerFactory.getLogger(ProxyIntro.class);
    public static void main(String[] args) {
        // Play the role of Spring here (there's no framework)
        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
        // TODO 2 : without changing anything below the line (w/o any interface)
        // TODO 3 : so that any new methods in Maths are automatically logged [hard]

        Maths real = new Maths();


//        InvocationHandler handler  = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Object r = method.invoke(real, args); // invok metoda reala
//                log.info("{}({})={}", method.getName(), args, r);
//                return r;
//            }
//        };
//        Maths proxy = (Maths) Proxy.newProxyInstance(ProxyIntro.class.getClassLoader(),
//                new Class<?>[]{Maths.class}, handler);
//        IMaths proxy = (IMaths) Proxy.newProxyInstance(ProxyIntro.class.getClassLoader(),
//                new Class<?>[]{IMaths.class}, handler);

        // In java, ca o metoda sa fie interceptata NU e nevoie sa impementezi vreo interfata.

        // Asta springu face:
        Callback callback  =new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object r = method.invoke(real, objects); // invok metoda reala
                log.info("{}({})={}", method.getName(), Arrays.toString(objects), r);
                return r;
            }
        };
        Maths proxy = (Maths) Enhancer.create(Maths.class, callback);


        SecondGrade secondGrade = new SecondGrade(proxy);

        new ProxyIntro().run(secondGrade);

        // TODO 4 : let Spring do its job, and do the same with an Aspect
        // SpringApplication.run(ProxyIntro.class, args);
    }

    // =============== THE LINE =================

//    @Autowired
    public void run(SecondGrade secondGrade) {
        System.out.println("At runtime...");
        secondGrade.mathClass();
    }
}

//contine, nu e
//has-a nu is-a
//
//favor composition (field)
// over inheritance (extends)
// > Decorator (aka delegate)
//class MathsWithLogger implements IMaths { // sa extinzi clase concrete e de evitat
//    private final IMaths delegate;
//    private static final Logger log = LoggerFactory.getLogger(MathsWithLogger.class);
//
//    MathsWithLogger(IMaths delegate) {
//        this.delegate = delegate;
//    }
//
//    @Override
//    public int sum(int a, int b) {
//        int r = delegate.sum(a, b);
//        log.info("sum({},{})={}", a, b, r);
//        return r;
//    }
//
//    @Override
//    public int product(int a, int b) {
//        int r = delegate.product(a, b);
//        log.info("product({},{})={}", a, b, r);
//        return r;
//    }
//}
// TASK: log the arguments and return value of every method in 'Maths' class

//@Service
class SecondGrade {
    private final Maths maths; // dependinta injectata aici de mana

    SecondGrade(Maths maths) {
        this.maths = maths;
    }

    public void mathClass() {
        System.out.println("Clasa pe care Spring ti-a injectat-o NU e MEREU clasa concreta pe care tu ai scris-o si o vezi in cod: "
            + maths.getClass().getName());
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
    }
}

//@Facade
class Maths {
    public int sum(int a, int b) {
        return a + b;
    }

    public int product(int a, int b) {
        int total = 0;
        for (int i = 0; i < a; i++) {
            total = sum(total, b);
        }
        return total;
    }
}


// Key Points
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
