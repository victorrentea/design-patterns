package victor.training.patterns.creational.singleton;

import org.apache.commons.io.IOUtils;
import org.jooq.lambda.Unchecked;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class SingletonIntro {
}

class BizService {
   public int bizMethod() {
      // Think about testing
      // TODO reuse B instance (performance)
      // TODO push life mgmt to B (getInstance)
      // TODO Test it
      String config = ServiceLocator.getInstance(ConfigManager.class).getConfig();
      if (config.equals("NOOP")) {
         return -1;
      }
      return 0;
   }
}

class ServiceLocator {
   private static final Map<Class<?>, Object> singletons = new HashMap<>();

   public static <T> T getInstance(Class<T> aClass) {
      return (T) singletons.computeIfAbsent(aClass,
          Unchecked.function(Class::newInstance));
   }
   public static <T> void setTestInstance(Class<T> aClass, T testDouble) {
      singletons.put(aClass, testDouble);
   }
}

class ConfigManager {

   public static ConfigManager getInstance() {
      return new ConfigManager();
   }
   private String config;

   public ConfigManager() {
      try (Reader reader = new FileReader("f.txt")) {
         config = IOUtils.toString(reader); // takes time
      } catch (IOException e) {
         throw new RuntimeException(e);
      }

   }

   public String getConfig() {
      return config;
   }
}