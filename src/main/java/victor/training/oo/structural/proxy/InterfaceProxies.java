package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class InterfaceProxies {
    public static void main(String[] args) throws FileNotFoundException {
        MateImpl mateImpl = new MateImpl();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.debug("SRI: Invoca fraeru metoda {}  cu param {}", method.getName(), Arrays.toString(args));
                return method.invoke(mateImpl, args);
            }
        };
        Mate mate = (Mate) Proxy.newProxyInstance(Mate.class.getClassLoader(),
                new Class<?>[]{Mate.class}, h);

        File file;
        OutputStream outputStream = new FileOutputStream(new File("a.b"));
        outputStream = new BufferedOutputStream(outputStream);

        bizniss(new MateCuLogDecorator(new MateImpl()));
    }

    private static void bizniss(Mate mate) {
        System.out.println(mate.suma(1, 1));
        System.out.println(mate.suma(2, 0));
        System.out.println(mate.suma(3, -1));
        System.out.println(mate.suma(3, 1));
        System.out.println(mate.product(3, 2));
    }
}

interface Mate {
    int suma(int a, int b);
    int product(int a, int b);
}

class MateCuLogDecorator implements Mate {
    private final Mate delegate;

    MateCuLogDecorator(Mate delegate) {
        this.delegate = delegate;
    }

    @Override
    public int suma(int a, int b) {
        System.out.println("Alt bustean");
        return delegate.suma(a,b);
    }

    @Override
    public int product(int a, int b) {
        System.out.println("Alt bustean");
        return delegate.product(a,b);
    }
}

class MateImpl implements Mate {
    @Override
    public int suma(int a, int b) {
        return a + b;
    }

    @Override
    public int product(int a, int b) {
        return a * b;
    }
}