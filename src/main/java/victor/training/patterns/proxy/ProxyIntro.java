package victor.training.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;
import victor.training.patterns.util.ThreadUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static java.lang.System.currentTimeMillis;

@SpringBootApplication
public class ProxyIntro {
    public static void main(String[] args) {
        // TODO 1 : Log the arguments of the Math.sum() method.
        // TODO 1bis: Then sometimes also measure it's run time (Decorator)
        // TODO 2 : Log without changing anything below the line w/o any interface (Proxy)
        // TODO 3 : so that any new methods in Maths are automatically logged
        //
        //        Maths maths = new Maths();
        //
        //        Callback h = new MethodInterceptor() {
        //            @Override
        //            public Object intercept(Object o, Method method, Object[] arguments, MethodProxy methodProxy) throws Throwable {
        //                System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(arguments));
        //                return method.invoke(maths, arguments);
        //            }
        //        };
        //        Maths mathsProxy = (Maths) Enhancer.create(Maths.class, h); // this is EXACTLY what spring/hibrernate/ejb/guice does under the hood.
        //
        //        SecondGrade secondGrade = new SecondGrade(mathsProxy);
        //
        //        new ProxyIntro().run(secondGrade);

        // Play the role of Spring here (there's no framework)
        // TODO 4 : let Spring do its job, and do the same with an Aspect
        SpringApplication.run(ProxyIntro.class, args);
    }

    // =============== THE LINE =================

    @Autowired
    public void run(SecondGrade secondGrade) throws Exception {
        System.out.println("At runtime...");
        secondGrade.mathClass();
    }
}


@Service
class SecondGrade {
    private final Maths maths;

    SecondGrade(Maths maths) {
        this.maths = maths;
    }

    //@Timed (micrometer)
    public void mathClass() throws Exception {
        System.out.println("Who am I talking with? " + maths.getClass());
        System.out.println("2+4=" + measure(()-> measure(() -> maths.sum(2, 4))));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
        System.out.println("3x3=" + maths.product(3, 3));
    }

    private <T> T measureWithEx(Callable<T> run) throws Exception {
        long t0 = currentTimeMillis();
        try {
            return run.call();
        } finally {
            long t1 = currentTimeMillis();
            System.out.println("TookFP " + (t1 - t0));
        }
    }
    private <T> T measure(Supplier<T> run) { // classic Java8
        long t0 = currentTimeMillis();
        try {
            return run.get();
        } finally {
            long t1 = currentTimeMillis();
            System.out.println("TookFP " + (t1 - t0));
        }
    }
    private void measure(Runnable run) throws Exception {
        long t0 = currentTimeMillis();
        try {
             run.run();
        } finally {
            long t1 = currentTimeMillis();
            System.out.println("TookFP " + (t1 - t0));
        }
    }
}

@Service
class Maths { // T
    public int sum(int a, int b) {
        ThreadUtils.sleepq(10); // Thinking...
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
// [1] Decorator = another implementation of the interface of the real class.
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
