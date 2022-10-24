package victor.training.patterns.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@SpringBootApplication
public class ProxyIntro {
    public static void main(String[] args) throws FileNotFoundException {
        // Play the role of Spring here (there's no framework)
        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
        // TODO 2 : without changing anything below the line (w/o any interface)
        // TODO 3 : so that any new methods in Maths are automatically logged [hard]

        Maths maths = new Maths();

//        new BufferedReader(new BufferedReader(new BufferedReader(new FileReader("cute.shit"))));
//        new JScrollPane(new JTable()); UI

        SecondGrade secondGrade = new SecondGrade(new MathsWithLog(new MathsWithLog(maths)));

        new ProxyIntro().run(secondGrade);

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

@Slf4j
class MathsWithLog implements IMaths { // decorator
    private final IMaths delegate;

    MathsWithLog(IMaths delegate) {
        this.delegate = delegate;
    }

    @Override
    public int sum(int a, int b) {
        log.info("Calling sum " + a + ", " + b);
        return delegate.sum(a,b);
    }

    @Override
    public int product(int a, int b) {
        log.info("Calling product " + a + ", " + b);
        return delegate.product(a, b);
    }
}

@Service
class SecondGrade { // my daughter
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
//class MathsLogged extends  Maths {}
interface IMaths{
    int sum(int a, int b);

    int product(int a, int b);
}
@Facade
class Maths implements IMaths {
    @Override
    public int sum(int a, int b) {
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
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
