package victor.training.patterns.proxy.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import victor.training.patterns.util.ThreadUtils;

public class Decorator {
    public static void main(String[] args) {
        // TODO 1 : Log the argx
        Maths maths = new Maths();

        SecondGrade secondGrade = new SecondGrade(new MathsCuLogging(maths));

        new Decorator().run(secondGrade);

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
class MathsCuLogging implements IMaths{
    private final Maths maths;

    MathsCuLogging(Maths maths) {
        this.maths = maths;
    }

    @Override
    public int sum(int a, int b) {
        int rezultat = maths.sum(a, b);
        System.out.println("Tatal maniac verifica suma: " +a + " si " + b + " i-a dat " + rezultat);
        return rezultat;
    }

    @Override
    public int product(int a, int b) {
        int rezultat = maths.product(a, b);
        System.out.println("Tatal maniac verifica produs: " +a + " si " + b + " i-a dat " + rezultat);

        return rezultat;
    }
}
// ------don't change anything bellow this line--------------------------------------
interface IMaths {
    int sum(int a, int b);
    int product(int a, int b);
}
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

class Maths implements IMaths { // T
    @Override
    public int sum(int a, int b) {
        ThreadUtils.sleepq(10); // Thinking...
        return a + b;
    }

    @Override
    public int product(int a, int b) {
        ThreadUtils.sleepq(30);
        int total = 0;
        for (int i = 0; i < a; i++) {
            total = sum(total, b);
        }
        return total;
    }
}

