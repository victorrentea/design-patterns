package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
	
	public static void main(String[] args) {
		
		MatematicaImpl peBune = new MatematicaImpl();
		
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("SRI: chemi cumva metoda " + method.getName() + " cu param " + Arrays.toString(args));
			
				return method.invoke(peBune, args); 
			}
		};
		
		Matematica mate = (Matematica) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(), 
				new Class<?>[] {Matematica.class}, h );
		
		System.out.println(mate.suma(1,1));
		System.out.println(mate.suma(2,0));
		System.out.println(mate.suma(3,-1));
		System.out.println(mate.suma(3,1));
	}

}

interface Matematica {
	Integer suma(int a, int b);
}
class MatematicaImpl implements Matematica{

	public Integer suma(int a, int b) {
		return a + b; // TODO
	}
	
}