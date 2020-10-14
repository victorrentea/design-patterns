package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyMagic {
	public static void main(String[] args) {
		
		ExpensiveOps realImpl = new ExpensiveOps();
		
		InvocationHandler h = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(args)
					);
				return method.invoke(realImpl, args);
			}
		};
		IExpensiveOps proxy = (IExpensiveOps) Proxy.newProxyInstance(ProxyMagic.class.getClassLoader(), new Class<?>[] { IExpensiveOps.class }, h);
		biz(proxy);
	}

	
	
	
	
	private static void biz(IExpensiveOps proxy) {
		System.out.println(proxy.isPrime(4));
		System.out.println(proxy.isPrime(4));
		System.out.println(proxy.isPrime(7));
		System.out.println(proxy.isPrime(8));
	}
}
