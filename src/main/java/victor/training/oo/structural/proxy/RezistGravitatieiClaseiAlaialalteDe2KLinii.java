package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class RezistGravitatieiClaseiAlaialalteDe2KLinii {

	public static void main(String[] args) {
		
		MateReal mateReal = new MateReal();
		MethodInterceptor callback = new MethodInterceptor() {
			@Override
			public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
				System.out.println("Cheama fraeru metoda " + method.getName() + " arg " + Arrays.toString(args));
				return method.invoke(mateReal, args);
			}
		};
		MateReal mate = (MateReal) Enhancer.create(MateReal.class, callback);
		//demo
		
		bizLogic(mate);
		
	}

	private static void bizLogic(MateReal tzeapa) {
		System.out.println("Oare cu cine vorghesc de fapt ? " + tzeapa.getClass());
		System.out.println(tzeapa.suma(1, 1));
		System.out.println(tzeapa.suma(2, 0));
		System.out.println(tzeapa.suma(3, -1));
		System.out.println(tzeapa.suma(3, 1));
		System.out.println(tzeapa.product(2, 1));
	}
	
}

class MateReal  {

	public Integer suma(int a, int b) {
		return a + b;
	}

	public Integer product(int a, int b) {
		return a * b;
	}
	
}
