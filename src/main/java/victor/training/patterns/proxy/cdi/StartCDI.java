package victor.training.patterns.proxy.cdi;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.interceptor.InterceptorBinding;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import java.io.IOException;
import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Logged {
}

public class StartCDI {
  public static void main(String[] args) throws IOException {
    Weld weld = new Weld().beanClasses(LoggedInterceptor.class, SimpleService.class, Greeter.class, HelloWorld.class)
        .disableDiscovery()
        .interceptors(LoggedInterceptor.class);
    WeldContainer container = weld.initialize();
    SimpleService service = container.instance().select(SimpleService.class).get();
    service.greet();
  }
}


@Singleton
class SimpleService {
  @Inject
  private Greeter greeter;

  public void greet() {
    greeter.greet();
  }
  public void printHello(@Observes ContainerInitialized event) {
    System.out.println("Hello CDI!");
  }
}

class Greeter {
  @Logged
  public void greet() {
    System.out.println("Hello World!");
  }
}
