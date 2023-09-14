package victor.training.patterns.proxy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.annotation.Transactional;

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
                System.out.println("Chem methoda " + method.getName() + " cu param " + Arrays.toString(params));
                return method.invoke(maths, params);
            }
        };
        Maths proxy = (Maths) Enhancer.create(Maths.class, h);

        SecondGrade secondGrade = new SecondGrade();

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
    private final Maths maths  /*= new Maths()*/; // 5: "new", eg din teste
    SecondGrade(Maths maths) {
        this.maths = maths;
    }
    public void mathClass() {
        System.out.println("Chem metode pe " + maths.getClass());
        System.out.println("2+4=" + maths.sum(2, 4));
        System.out.println("1+5=" + maths.sum(1, 5));
        System.out.println("2x3=" + maths.product(2, 3));
    }
}
// stiind ca proxy=subclasa care face @override la met publice
// ce pot face mai jos sa STRIC PROXY-URILE: "Tzepe cu proxy-uri"

/*1:final->Crash la start*/
class Maths {
//    @Secured("ROLE_ADMIN")
    @Transactional // chemata din alta clasa deschide Tx, chemata local NU.
    public /*2:final->IGNORED*/ int sum(int a, int b) {
        return a + b;
    }
    public /*3:static->IGNORED*/ int product(int a, int b) {
        int result = 0;
        for (int i = 0; i < a; i++) {
            result = sum(result, b); // 4⭐️⭐️⭐️⭐️⭐️:apel local NU e interceptat
        }
        return result;
    }
}


// Key Points
// [2] Class Proxy using CGLIB (Enhancer) extending the proxied class
// [3] Spring Cache support [opt: redis]
// [4] Custom @Aspect, applied to methods in @Facade
// [6] Tips: self proxy, debugging, final
// [7] OPT: Manual proxying using BeanPostProcessor
