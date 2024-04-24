//package victor.training.patterns.singleton.guice;
//
//import com.google.inject.*;
//import com.google.inject.nameg.Names;
//import org.mockito.Mockito;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Named;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
//public class GuiceShowcase {
//   public static void main(String[] args) {
//      Injector injector = Guice.createInjector(new AbstractModule() {
//         @Override
//         protected void configure() {
//            // optional customization
////            bind(SomeInterface.class).to(SomeImpl.class);
//            bind(Config.class).toProvider(() -> Config.readFromFile()).in(Scopes.SINGLETON);
////            bind(ExternalClient.class).toProvider(() -> Mockito.mock(ExternalClient)).in(Scopes.SINGLETON);
//
//            Properties properties = new Properties();
//            try {
//               properties.load(new FileReader("Test.properties"));
//               System.out.println("Props: " + properties);
//
////               Propertyenum.for    (key, default) -> put in the map
//               HashMap<String, String> p = new HashMap<>(properties
//                       .entrySet()
//                       .stream()
//                       .collect(Collectors.toMap(e -> "prop:" + e.getKey(), e->e.getValue().toString())));
//               Names.bindProperties(binder(), p);
////               Names.bindProperties(binder(), properties);
//            } catch (FileNotFoundException e) {
//               System.out.println("The configuration file Test.properties can not be found");
//            } catch (IOException e) {
//               System.out.println("I/O Exception during loading configuration");
//            }
//         }
//      });
//      A instance = injector.getInstance(A.class);
//      instance.methodA();
//
//      injector.getInstance(B.class).methodB();
//      injector.getInstance(B.class).methodB();
//   }
//}
//
//class A {
//   @Inject // com.google.inject or javax.inject
//   private B b;
//   @Inject
//   private C c;
//   @Inject
//   private Config config;
//
//   @PostConstruct
//   // does NOT work out of the bok, but see  https://stackoverflow.com/questions/2093344/guice-call-init-method-after-instantinating-an-object
//   public void initA() {
//      System.out.println("init A");
//   }
//
//   public void methodA() {
//      b.methodB();
//      c.methodC();
//      System.out.println(config.getHost());
//   }
//}
//
//enum MambuProperties {
//   SOME_PROP("prop");
//
//   public final String key;
//
//   MambuProperties(String key) {
//      this.key = key;
//   }
//}
//class C {
//   private B b;
//   private Config config;
//   private final String prop;
//
//   @Inject
////   public C(B b, Config config, @Named("prop:prop") String prop) {
//   public C(B b, Config config, @Named("prop:key.from.file") String prop) {
//      this.b = b;
//      this.config = config;
//      this.prop = prop;
//   }
//
//   public void methodC() {
//      b.methodB();
//      System.out.println("Injected property from file: " + prop );
//      System.out.println(config.getHost());
//   }
//}
//
//@Singleton // without this, B is created for each injection point above (2 times)
//class B implements IB {
//   public void methodB() {
//      System.out.println("HALO! " + this);
//   }
//}
//
//class Config {
//   private final String host;
//   private final int port;
//
//   Config(String host, int port) {
//      this.host = host;
//      this.port = port;
//   }
//
//   public static Config readFromFile() {
//      System.out.println("Reading config from disk");
//      return new Config("localhost", 8080);
//   }
//
//   public int getPort() {
//      return port;
//   }
//
//   public String getHost() {
//      return host;
//   }
//}
//
//interface IB { // @Inject-ing the interface requires bind().to() in your module
//   void methodB();
//}
