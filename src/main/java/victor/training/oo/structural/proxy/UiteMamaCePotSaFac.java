package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class UiteMamaCePotSaFac {

	public static void main(String[] args) {
		// LOTR ep 2 saruman the white
		
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("se invoca metoda " + method.getName() + 
						" cu param " + Arrays.toString(args));
				return true; // TODO
			}
		};
		IExpensiveOps ops = (IExpensiveOps) Proxy.newProxyInstance(
				UiteMamaCePotSaFac.class.getClassLoader(),
				new Class<?>[] {IExpensiveOps.class}, 
				h);
		
		System.out.println(ops.isPrime(2));
		System.out.println(ops.isPrime(4));
//		IExpensiveOps.class
		
	}
}
