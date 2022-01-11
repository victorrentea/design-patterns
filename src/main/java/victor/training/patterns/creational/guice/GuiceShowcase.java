package victor.training.patterns.creational.guice;

import com.google.inject.*;

import javax.annotation.PostConstruct;

public class GuiceShowcase {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new AbstractModule() {
         @Override
         protected void configure() {
            // optional customization
//            bind(SomeInterface.class).to(SomeImpl.class);
            bind(Config.class).toProvider(() -> Config.readFromFile()).in(Scopes.SINGLETON);
         }
      });
      A instance = injector.getInstance(A.class);
      instance.methodA();

      injector.getInstance(B.class).methodB();
      injector.getInstance(B.class).methodB();
   }
}

class A {
   @Inject // com.google.inject or javax.inject
   private B b;
   @Inject
   private C c;
   @Inject
   private Config config;

   @PostConstruct
   // does NOT work out of the bok, but see  https://stackoverflow.com/questions/2093344/guice-call-init-method-after-instantinating-an-object
   public void initA() {
      System.out.println("init A");
   }

   public void methodA() {
      b.methodB();
      c.methodC();
      System.out.println(config.getHost());
   }
}

class C {
   private B b;
   private Config config;

   @Inject
   public C(B b, Config config) {
      this.b = b;
      this.config = config;
   }

   public void methodC() {
      b.methodB();
      System.out.println(config.getHost());
   }
}

@Singleton // without this, B is created for each injection point above (2 times)
class B implements IB {
   public void methodB() {
      System.out.println("HALO! " + this);
   }
}

class Config {
   private final String host;
   private final int port;

   Config(String host, int port) {
      this.host = host;
      this.port = port;
   }

   public static Config readFromFile() {
      System.out.println("Reading config from disk");
      return new Config("localhost", 8080);
   }

   public int getPort() {
      return port;
   }

   public String getHost() {
      return host;
   }
}

interface IB { // @Inject-ing the interface requires bind().to() in your module
   void methodB();
}
