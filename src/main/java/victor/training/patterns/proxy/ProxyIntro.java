package victor.training.patterns.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@SpringBootApplication
public class ProxyIntro {
    public static void main(String[] args) throws FileNotFoundException {

//        new BufferedReader(new BufferedReader(new BufferedReader(new FileReader("cute.shit"))));
//        new JScrollPane(new JTable()); UI

//        SecondGrade secondGrade = new SecondGrade(new MathsWithLog(new MathsWithLog(maths)));

//
//        // Play the role of Spring here (there's no framework)
//        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
//        // TODO 2 : without changing anything below the line (w/o any interface)
//        // TODO 3 : so that any new methods in Maths are automatically logged [hard]
//
//        Maths realMaths = new Maths(); // your spring bean
//
//        // WARNING: you should not write such code in your production!
//        // it's just to demonstrate the magic behind spring/ejb/guice/hibernate
//
//        Callback h = new MethodInterceptor() {
//            @Override
//            public Object intercept(Object o,
//                                    Method method,
//                                    Object[] arguments, MethodProxy methodProxy) throws Throwable {
//                System.out.println("Calling " + method.getName() + " with " + Arrays.toString(arguments)) ;
//                // call real method:
////                if (method.isAnnotationPresent(Transactional.class)) {
////                    // stuff
////                }
//                return method.invoke(realMaths, arguments);
//            }
//        };
//        Maths mathsProxy = (Maths) Enhancer.create(Maths.class, h); // CGLIB library
//        // generate a dynamic subclass of your class and @Overrides all its public methods
//        // calling "intercept" method above.
//
//
//        // in plain java, what can I pass to a method requiring a Maths instance?
//        // a) new Maths(),
//        // b) new Maths() {   }
////        Maths mathsProxy = new Maths() { // anonymous subclass
////            @Override
////            public int sum(int a, int b) {
////                System.out.println("Guess who's back !?");
////                return super.sum(a, b);
////            }
////        };
//
//        SecondGrade secondGrade = new SecondGrade(mathsProxy);
//
//        new ProxyIntro().run(secondGrade);

        I i = (I)Proxy.newProxyInstance(I.class.getClassLoader(), new Class<?>[]{I.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "Interface Proxies";
            }
        });
        System.out.println(i.hi());

        // TODO 4 : let Spring do its job, and do the same with an Aspect
         SpringApplication.run(ProxyIntro.class, args);
    }
interface I {
    String hi();
}


    // =============== THE LINE =================

    @Autowired
    public void run(SecondGrade secondGrade) { // SPring passes the SecondGrade object
        System.out.println("At runtime...");
        secondGrade.mathClass();
    }

}


@Service
class SecondGrade { // my daughter
    private final Maths maths;

    SecondGrade(Maths maths) {
        this.maths = maths;
    } // injected by Spring. And guess what? the matsh inhjected is not the real object,
    // but a PROXY

    public void mathClass() {
        System.out.println("SPring has to trick you in order to intercept methods: in gives you not th real Maths, but a proxy: " + maths.getClass());
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
        System.out.println("3x3=" + maths.product(3, 3));
    }
}
@Facade
/*final*/ class Maths { // breaks startup
//    @Secured("ROLE_ADMIN") // ignored
    /*final*/ public int sum(int a, int b) { // silently ignored at runtime. No error. 🤬 BAD worse than exception.
        return a + b;
    }

//    @Transactional
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
