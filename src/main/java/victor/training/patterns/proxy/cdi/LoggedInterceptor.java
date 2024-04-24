package victor.training.patterns.proxy.cdi;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.io.Serializable;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {
  @AroundInvoke
  public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
    System.out.println("Entering method: "
                       + invocationContext.getMethod().getName() + " in class "
                       + invocationContext.getMethod().getDeclaringClass().getName());

    return invocationContext.proceed();
  }
}
