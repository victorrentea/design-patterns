package victor.training.patterns.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecondGrade {


    public SecondGrade(Maths maths) {
        this.maths = maths;
    }


    public static void main(String[] args) {

        Immutable immutable = new Immutable();
        for (String string : immutable.getStrings()) {
            System.out.println(string);
        }
        System.out.println("and now... changing");
//        immutable.getStrings().clear(); // RUNTIME ERROR! PANIC.
        // wouldn't it be better that the List<> would NOT have eg. add()

        // INTERFACE SEGREGATION PRINCIPLE (sol[I]d) VIOLATION

//        new SecondGrade(new MyClass(new Maths())).method();

        Maths real = new Maths();
        Callback h = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("Stuff " + method.getName() + " and args " + Arrays.toString(objects));
                return method.invoke(real, objects);
            }
        };
        Maths proxy = (Maths) Enhancer.create(Maths.class, h);

        new SecondGrade(proxy).method();

    }

    private final Maths maths;

    public void method() {
        System.out.println("You are not really receiving the bare impl  class:  " + maths.getClass() );
        System.out.println(maths.sum(1, 2));
        System.out.println(maths.sum(1, 2));
        System.out.println(maths.sum(1, 2));
        System.out.println(maths.sum(1, 2));
    }

}
class MyClass extends Maths{ // this is what spring does for your annotations.
    // BUT spring does not write code, instead it generates the bytecode dynamically with CGLIB

    private final Maths maths;

    MyClass(Maths maths) {
        this.maths = maths;
    }
    public int sum(int a, int b) {
        System.out.println("mee too");
        return maths.sum(a, b);
    }
}
//interface List {immutable}
//interface MutableList extends  List {}

class Maths {
    public int sum(int a, int b) {
        return a + b;
    }
}

//@Service

//SomeClass.prototype.newMetod =



class Immutable {
    private final List<String> strings = new ArrayList<>();

    public List<String> getStrings() {
//        return new ArrayList<>(strings);
        return Collections.unmodifiableList(strings);
        // i'm using a static factory method (to allow JDK to decide what class they return to me)
        // to get back a decorated list that blocks all attempts to CHANGE to original list.
//        return new Collections.UnmodifiableRandomAccessList<>(strings); // not visible
    }
}