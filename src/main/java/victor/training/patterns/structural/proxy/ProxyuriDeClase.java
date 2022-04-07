package victor.training.patterns.structural.proxy;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyuriDeClase {

   public static void main(String[] args) {
      // springu sub capota
      Matematica impl = new Matematica();

//      Clasa2.class.getMethod("mate").invoke()

      Callback h = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("Cheama " + method.getName() + " + cu arg " + Arrays.toString(args));
            return method.invoke(impl, args);
         }
      };
      Matematica proxyLaMate = (Matematica) Enhancer.create(Matematica.class, h);
//      Matematica proxyLaMate = new Matematica() {
//         @Override
//         public int impartiri(int impartit, int impartitor) {
//            return super.impartiri(impartit, impartitor);
//         }
//      };

      Clasa2 clasa2 = new Clasa2(proxyLaMate);
      clasa2.oraDeMate();
   }

}

@Service
@RequiredArgsConstructor
class Clasa2 {
   private final Matematica matematica;

   public void oraDeMate() {
      System.out.println("Hai sa puneti mana pe Vasilica : uite proxyului: " + matematica.getClass());
      System.out.println(matematica.impartiri(12, 4));
      System.out.println(matematica.inmultire(3, 4));
   }
}

@Service
    /*final*/
class Matematica {
   @Transactional
   public /*final*/ int impartiri(int impartit, int impartitor) {
      return impartit / impartitor;
   }

   public int inmultire(int a, int b) {
      System.out.println("Sa verific, cat facea 16 / 4 = " + impartiri(16, 4));
      return a * b;
   }
}

@Component
@Aspect
class LoggingAspect {
   @Around("execution(* victor.training.patterns..*.*(..))")
   public Object logeaza(ProceedingJoinPoint pjp) throws Throwable {
      System.out.println("Chem metoda " + pjp.getSignature().getName() +
                         " cu param " + Arrays.toString(pjp.getArgs()));
      return pjp.proceed();
   }
}


//System.out.println("Cheama " + method.getName() + " + cu arg " + Arrays.toString(args));