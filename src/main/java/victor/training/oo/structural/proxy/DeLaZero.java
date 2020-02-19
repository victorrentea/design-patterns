package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DeLaZero {

	public static void main(String[] args) {
		Mate mateReal = new MateImpl();
		
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object arg0, Method method, Object[] args) throws Throwable {
				System.out.println("SRI: Cumva o chemi pe " + method.getName() + " cu param " + Arrays.toString(args));
				return method.invoke(mateReal, args);
			}
		};
		
		Mate mate = (Mate) Proxy.newProxyInstance(DeLaZero.class.getClassLoader(), 
				new Class<?>[] {Mate.class}, h);
		
		metodaDeBiznis(mate);
	}

	private static void metodaDeBiznis(Mate mate) {
		System.out.println(mate.sum(1, 1));
		System.out.println(mate.sum(2, 0));
		System.out.println(mate.sum(3, -1));
		System.out.println(mate.sum(3, 1));
		System.out.println(mate.produs(3, 2));
	}
}

class MateImpl implements Mate {
	public int sum(int a, int b) {
		return a + b;
	}
	public int produs(int i, int j) {
		return i *j;
	}
}
interface Mate{
	int sum(int a, int b);

	int produs(int i, int j);
}