package victor.training.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import victor.training.patterns.util.ThreadUtils;

import static java.lang.System.currentTimeMillis;

@SpringBootApplication
public class ProxyIntro {
    public static void main(String[] args) {
        // TODO 1 : Log the arguments of the Math.sum() method.
        // TODO 1bis: Then sometimes also measure it's run time (Decorator)
        // TODO 2 : Log without changing anything below the line w/o any interface (Proxy)
        // TODO 3 : so that any new methods in Maths are automatically logged

        IMaths maths = new Maths();


        SecondGrade secondGrade = new SecondGrade(new LoggingMathDecorator(new MonitoringMathDecorator(maths)));

        new ProxyIntro().run(secondGrade);

        // Play the role of Spring here (there's no framework)
        // TODO 4 : let Spring do its job, and do the same with an Aspect
        // SpringApplication.run(ProxyIntro.class, args);
    }

    // =============== THE LINE =================

    @Autowired
    public void run(SecondGrade secondGrade) {
        System.out.println("At runtime...");
        secondGrade.mathClass();
    }
}
class MonitoringMathDecorator implements IMaths {
    private final IMaths delegate;

    MonitoringMathDecorator(IMaths math) {
        this.delegate = math;
    }

    @Override
    public int sum(int a, int b) {
        long t0 = currentTimeMillis();
        int sum = delegate.sum(a, b);
        long t1 = currentTimeMillis();
        System.out.println("Took " + (t1-t0));
        return sum;
    }

    @Override
    public int product(int a, int b) {
        return delegate.product(a, b);
    }
}
class LoggingMathDecorator implements IMaths {
    private final IMaths delegate;

    LoggingMathDecorator(IMaths math) {
        this.delegate = math;
    }

    @Override

    public int sum(int a, int b) {
        System.out.println("Calling sum with " + a+","+b);
        return delegate.sum(a,b);
    }

    @Override
    public int product(int a, int b) {
        return delegate.product(a, b);
    }
}
// ------don't change anything bellow this line--------------------------------------



@Service
class SecondGrade {
    private final IMaths maths;

    SecondGrade(IMaths maths) {
        this.maths = maths;
    }

    public void mathClass() {
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
    }
}

interface IMaths {
    int sum(int a, int b);
    int product(int a, int b);
}

@Facade
class Maths implements IMaths { // T
    @Override
    public int sum(int a, int b) {
        ThreadUtils.sleepq(10); // Thinking...
        return a + b;
    }

    @Override
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
