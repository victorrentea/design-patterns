package victor.training.oo.structural.proxy;

import java.util.concurrent.Callable;


public class AOPCuJava8 {
	{
		Utils.tryCatch(this::mDeRulat);
		Integer i = Utils.tryCatch(this::treaba);
		int a=1, b=2, c=3;
		Integer i2 = Utils.tryCatch(() -> mDeRulatCuParamMulti(a,b,c));
	}
	void mDeRulat() {
		
	}
	int mDeRulatCuParamMulti(int a, int b, int c) {
		return 2;
	}
	int treaba() {
		return 1;
	}
	static class Utils {
		static void tryCatch(Runnable r) {
			try {
				r.run();
			} catch (Throwable tu) {
				
			}
		}
		static <T> T tryCatch(Callable<T> r) {
			try {
				return r.call();
			} catch (Throwable tu) {
				 throw new RuntimeException(tu);
			}
		}
	}
	
}
