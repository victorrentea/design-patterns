package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class RezistGravitatieiClaseiAlaialalteDe2KLinii {

	public static void main(String[] args) {
		
		MateReal mateReal = new MateReal();
		InvocationHandler h = new InvocationHandler() {
			@Override
			public Object invoke(Object arg0, Method method, Object[] args) throws Throwable {
				System.out.println("Cheama fraeru metoda " + method.getName() + " arg " + Arrays.toString(args));
				return method.invoke(mateReal, args);
			}
		};
		Mate mate = (Mate) Proxy.newProxyInstance(
				RezistGravitatieiClaseiAlaialalteDe2KLinii.class.getClassLoader(), 
				new Class<?>[] {Mate.class}, h);
		
		//demo
		
		bizLogic(mate);
		
	}

	private static void bizLogic(Mate mate) {
		System.out.println(mate.suma(1, 1));
		System.out.println(mate.suma(2, 0));
		System.out.println(mate.suma(3, -1));
		System.out.println(mate.suma(3, 1));
		System.out.println(mate.product(2, 1));
	}
	
}
interface Mate {
	Integer suma(int a, int b);
	Integer product(int a, int b);
}

class MateReal implements Mate {

	@Override
	public Integer suma(int a, int b) {
		return a + b;
	}

	@Override
	public Integer product(int a, int b) {
		return a * b;
	}
	
}
