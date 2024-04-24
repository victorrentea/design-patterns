package victor.training.patterns.proxy;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InterceptorBinding;
import jakarta.interceptor.InvocationContext;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.io.Serializable;
import java.lang.annotation.*;
import java.util.Arrays;

public class ProxyIntro {
//    public static void main(String[] args) {
//        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
//        // TODO 2 : without changing anything below the line (w/o any interface)
//        // TODO 3 : so that any new methods in Maths are automatically logged [hard]
//
//        Maths realInstance = new Maths();
//
//        Callback h = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("Method " + method.getName() + " called with args: " + Arrays.toString(args));
//                Object r = method.invoke(realInstance, args);
//                System.out.println("Returning " + r);
//                return r;
//            }
//        };
//        Maths proxy = (Maths) Enhancer.create(Maths.class, h); // generate a dynamic subclass of your bean with the
//        // CGLIB,Bytebuggy,javaassist framewokrk
//
//        SecondGrade secondGrade = new SecondGrade(proxy);
//
//        System.out.println("At runtime...");
//        secondGrade.mathClass();
//    }

  public static void main(String[] args) {
    // CDI
    Weld weld = new Weld().beanClasses(LoggedInterceptor.class, SecondGrade.class, Maths.class, TimedInterceptor.class)
        .disableDiscovery()
        .interceptors(LoggedInterceptor.class, TimedInterceptor.class)
        // @Cacheable @Secured(DOCTOR)
        ;
    WeldContainer container = weld.initialize();
    SecondGrade service = container.instance().select(SecondGrade.class).get();
    service.mathClass();
  }
}

//class LoggedMath extends Maths {
//  @Override
//  public int sum(int a, int b) {
//    System.out.println("Method sum called with args: " + a + ", " + b);
//    return super.sum(a, b);
//  }
//}

//without changing any line of code below, print the arguments of any invocation of a method in Maths
@InterceptorBinding // this
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Logged {
}

@InterceptorBinding // this
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Timed {
}

@Logged
@Interceptor
class LoggedInterceptor implements Serializable {
  @AroundInvoke
  public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
    System.out.println("Entering method: "
                       + invocationContext.getMethod().getName() + " in class "
                       + invocationContext.getMethod().getDeclaringClass().getName() +
                       " with args: " + Arrays.toString(invocationContext.getParameters()));

    return invocationContext.proceed(); // allows the real method to be executed
  }
}

@Logged
@Interceptor
class TimedInterceptor implements Serializable {
  @AroundInvoke
  public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
    System.out.println("Enter TImed");
    long start = System.currentTimeMillis();
    Object result = invocationContext.proceed();
    long duration = System.currentTimeMillis() - start;
    System.out.println("Method " + invocationContext.getMethod().getName() + " took " + duration + " ms");
    return result;
  }
}


class SecondGrade {
  @Inject
  private Maths maths;

  public void mathClass() {
    System.out.println("Who are you? " + maths.getClass());
    System.out.println("2+4=" + maths.sum(2, 4));
    System.out.println("1+5=" + maths.sum(1, 5));
    System.out.println("2x3=" + maths.product(2, 3));
  }
}

@Logged
class Maths {
  //  @Secured("DOCTOR_ROLE")
//  @MyTransactional
//  @Timed
  @Timed
  public int sum(int a, int b) {
    return a + b;
  }

  public int product(int a, int b) {
    int r = 0;
    for (int i = 0; i < a; i++) {
      r = sum(r, b); // local method calls do NOT go through CDI interceptors called by @
    }
    return r;
  }
}


// Key Points
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
