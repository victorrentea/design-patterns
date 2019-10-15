package victor.training.oo.structural.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import lombok.extern.slf4j.Slf4j;

public class Golem {
    public static void main(String[] args) {
    	Matematica matePeBune = new Matematica();
    	MethodInterceptor callback = new MethodInterceptor() {
			@Override
			public Object intercept(Object arg0, Method method, Object[] args1, MethodProxy arg3) throws Throwable {
				System.out.println("SRI: cumva chemi metoda " + 
							method.getName() + " cu param " + Arrays.toString(args1));
				return method.invoke(matePeBune, args1);
			}
		};
		//magie
    	Matematica mate = (Matematica) Enhancer.create(Matematica.class, callback);
    	
    	deBusiness(mate);
    }
    
    public static void deBusiness(Matematica mate) {
    	System.out.println("Oare cu cine vorbesc ? " + mate.getClass());
    	System.out.println(mate.sum(1, 1));
    	System.out.println(mate.sum(2, 0));
    	System.out.println(mate.sum(3, -1));
    	System.out.println(mate.sum(3, 2));
    	
    	
    	System.out.println(mate.product(3, 2));


    }
}


@Slf4j
class Matematica {
	
	// @EJB Matematica myself;
	
//	@TransactionAttribute(REQUIRES_NEW)
	public Integer sum(int a, int b) {
//		log.debug("sum(" + a + "," + b + ")");
		return a + b;
	}
    public Integer product(int a, int b) {
//    	log.debug("sum(" + a + "," + b + ")");
    	sum(100,100);
        return a * b;
    }
}