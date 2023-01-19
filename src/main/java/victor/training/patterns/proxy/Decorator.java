package victor.training.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import victor.training.patterns.util.ThreadUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Decorator {
  public static void main(String[] args) {
    // TODO 1 : Log the argx
    Maths maths = new Maths();

    Callback h = new MethodInterceptor() {
      @Override
      public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object rezultat = method.invoke(maths, objects);
        System.out.println("Tatal maniac verifica "+ method.getName()+": " + Arrays.toString(objects) + " i-a dat " + rezultat);
        return rezultat;
      }
    };
    Maths proxy = (Maths) Enhancer.create(Maths.class, h);

    SecondGrade secondGrade = new SecondGrade(proxy);

    new Decorator().run(secondGrade);

    // Play the role of Spring here (there's no framework)
    // TODO 4 : let Spring do its job, and do the same with an Aspect
    // SpringApplication.run(ProxyIntro.class, args);
  }

  // =============== THE LINE =================

  public void run(SecondGrade secondGrade) {
    System.out.println("At runtime...");
    secondGrade.mathClass();
  }
}

//class MathsCuLogging extends Maths{
//    private final Maths maths;
//
//    MathsCuLogging(Maths maths) {
//        this.maths = maths;
//    }
//
//    @Override
//    public int sum(int a, int b) {
//        int rezultat = maths.sum(a, b);
//        System.out.println("Tatal maniac verifica suma: " +a + " si " + b + " i-a dat " + rezultat);
//        return rezultat;
//    }
//
//        @Override
//        public int product(int a, int b) {
//            int rezultat = maths.product(a, b);
//            System.out.println("Tatal maniac verifica produs: " +a + " si " + b + " i-a dat " + rezultat);
//
//            return rezultat;
//    }
//}
// ------don't change anything bellow this line--------------------------------------
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

class Maths { // T
  public int sum(int a, int b) {
    ThreadUtils.sleepq(10); // Thinking...
    return a + b;
  }

  public int product(int a, int b) {
    ThreadUtils.sleepq(30);
    int total = 0;
    for (int i = 0; i < a; i++) {
      total = sum(total, b);
    }
    return total;
  }
}

