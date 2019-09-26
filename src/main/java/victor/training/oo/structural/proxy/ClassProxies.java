package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class ClassProxies {
	
	public static void main(String[] args) {
		
		MatematicaImpl peBune = new MatematicaImpl();
		
		Callback callback = new MethodInterceptor() {
			public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
				System.out.println("SRI: chemi cumva metoda " + method.getName() + " cu param " + Arrays.toString(args));
				return method.invoke(peBune, args);
			}
		};
		MatematicaImpl mate = (MatematicaImpl) Enhancer.create(MatematicaImpl.class, callback );
		
		System.out.println(mate.suma(1,1));
		System.out.println(mate.suma(2,0));
		System.out.println(mate.suma(3,-1));
		System.out.println(mate.suma(3,1));
	}

}

class MatematicaImpl {

	public Integer suma(int a, int b) {
		return a + b; // TODO
	}
	
}