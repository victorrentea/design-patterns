package victor.training.patterns.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyIntro {
    public static void main(String[] args) {
        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
        // TODO 2 : without changing anything below the line (w/o any interface)
        // TODO 3 : so that any new methods in Maths are automatically logged [hard]

        Maths realInstance = new Maths();

        Callback h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Method " + method.getName() + " called with args: " + Arrays.toString(args));
                Object r = method.invoke(realInstance, args);
                System.out.println("Returning " + r);
                return r;
            }
        };
        Maths proxy = (Maths) Enhancer.create(Maths.class, h); // generate a dynamic subclass of your bean

        SecondGrade secondGrade = new SecondGrade(proxy);

        System.out.println("At runtime...");
        secondGrade.mathClass();
    }
}

//without changing any line of code below, print the arguments of any invocation of a method in Maths

class SecondGrade {
    private final Maths maths;
    SecondGrade(Maths maths) {
        this.maths = maths;
    }

    public void mathClass() {
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
    }
}

class Maths {
    public int sum(int a, int b) {
        return a + b;
    }
    public int product(int a, int b) {
//        int total = 0;
//        for (int i = 0; i < a; i++) {
//            total = sum(total, b);
//        }
//        return total;
        return a * b;
    }
}


// Key Points
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
