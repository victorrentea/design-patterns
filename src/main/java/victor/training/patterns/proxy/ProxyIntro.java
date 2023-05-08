package victor.training.patterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SpringBootApplication
public class ProxyIntro {
    public static void main(String[] args) {
        // Play the role of Spring here (there's no framework)
        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
        // TODO 2 : without changing anything below the line (w/o any interface)
        // TODO 3 : so that any new methods in Maths are automatically logged [hard]

        IMaths maths = new MathsWithLogger(new Maths());

        SecondGrade secondGrade = new SecondGrade(maths);

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
interface IMaths {
    int sum(int a, int b);
    int product(int a, int b);
}

//contine, nu e
//has-a nu is-a
//
//favor composition (field)
// over inheritance (extends)
// > Decorator (aka delegate)
class MathsWithLogger implements IMaths { // sa extinzi clase concrete e de evitat
    private final IMaths delegate;
    private static final Logger log = LoggerFactory.getLogger(MathsWithLogger.class);

    MathsWithLogger(IMaths delegate) {
        this.delegate = delegate;
    }

    @Override
    public int sum(int a, int b) {
        int r = delegate.sum(a, b);
        log.info("sum({},{})={}", a, b, r);
        return r;
    }

    @Override
    public int product(int a, int b) {
        int r = delegate.product(a, b);
        log.info("product({},{})={}", a, b, r);
        return r;
    }
}
// TASK: log the arguments and return value of every method in 'Maths' class

//@Service
class SecondGrade {
    private final IMaths maths; // dependinta injectata aici de mana

    SecondGrade(IMaths maths) {
        this.maths = maths;
    }

    public void mathClass() {
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
    }
}

//@Facade
class Maths implements IMaths {
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
