package victor.training.oo.structural.proxy;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.jooq.lambda.Unchecked;

public class IaUiteMamaCePotSaFac {

	public static void main(String[] args) {
		
		ExpensiveOps realImpl = new ExpensiveOps();
		
		InvocationHandler h = new InvocationHandler() {
			private final Map<Object, Object> cache = new HashMap<>();
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("SRI: tu suni pe : " + method.getName());
				return cache.computeIfAbsent(args[0], Unchecked.function(a -> method.invoke(realImpl, args)));
			}
		};
		IExpensiveOps golem = (IExpensiveOps) Proxy.newProxyInstance(IaUiteMamaCePotSaFac.class.getClassLoader(), 
				new Class<?>[] {IExpensiveOps.class}, h);
		System.out.println(golem.isPrime(10000169));
		System.out.println(golem.isPrime(10000169));
		System.out.println(golem.isPrime(10000169));
		
		
		String hash = golem.hashAllFiles(new File("."));
		System.out.println(hash);
		
	}
}
