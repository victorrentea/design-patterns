package victor.training.patterns.proxy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootApplication
public class ProxyIntro {
    public static void main(String[] args) {
        // Play the role of Spring here (there's no framework)
        // TODO 1 : LOG the arguments of any invocation of a method in Maths w/ decorator
        // TODO 2 : without changing anything below the line (w/o any interface)
        // TODO 3 : so that any new methods in Maths are automatically logged [hard]
        Maths maths = new Maths();

        Callback h = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
//                Maths.class.getDeclaredMethod("", int) // << ITI BATI JOC DE JAVA si de design / encapsulare
                System.out.println("Chem methoda " + method.getName() + " cu param " + Arrays.toString(params));
                return method.invoke(maths, params);
            }
        };
        Maths proxy = (Maths) Enhancer.create(Maths.class, h);

        SecondGrade secondGrade = new SecondGrade(proxy);

        secondGrade.mathClass();
    }
//    @Autowired
//    public void run(SecondGrade secondGrade) {
//        System.out.println("At runtime...");
//        secondGrade.mathClass();
//    }
}
// ASA FACE PE SUB SPRING DE FAPT
//genereaza o subclasa la clasa ta si suprascrie toate metodele publice
// injecteaza apoi aceasta instanta peste tot.

// spring va genera asta: asta e un proxy. Miroase ca originalul, dar face chestii in plus
//class MathsPrim extends Maths {
//    private final Maths maths; // compozitie
//    MathsPrim(Maths maths) {
//        this.maths = maths;
//    }
//
//    @Override
//    public int sum(int a, int b) {
//        System.out.println("sum ("+a+","+b+")");
////        return super.sum(a, b); // mostenire
//        return maths.sum(a, b); // compozitie❤️
//    }
//    @Override
//    public int product(int a, int b) {
//        System.out.println("product ("+a+","+b+")");
//        return maths.product(a, b);
//    }
//    // am intercept apelul si am facut chestii inainte sa deleg in superclasa.
//}

// TODO intercepteaza si logeaza orice apel pe care SecondGrade il face catre Maths
//   ca sa logezi parametrii✅
// TODO logeaza timpul executiei t0=  t1=
// =============== THE LINE =================
//nu ai voie sa editezi sub linia asta!!!
// (ca si cum nu ai cum sa alterezi codul scris de developer

class SecondGrade {
    private final Maths maths;
    SecondGrade(Maths maths) {
        this.maths = maths;
    }

    public void mathClass() {
        System.out.println("Chem metode pe " + maths.getClass());
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("2+8=" + maths.sum(2, 8));
        System.out.println("2+123=" + maths.sum(2, 123));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
    }
}
class Maths {
    public int sum(int a, int b) {
        return a + b;
    }
    public int product(int a, int b) {
        return a*b;
    }
}


// Key Points
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
