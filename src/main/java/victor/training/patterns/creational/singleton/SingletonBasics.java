package victor.training.patterns.creational.singleton;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@Slf4j
public class SingletonBasics {
   public static void main(String[] args) {
      log.debug("Start");
      log.debug("Out: " + new BizService().bizMethod());
      log.debug("Out: " + new BizService().bizMethod());
   }
}

@Service
class BizService {
   @Autowired
   ConfigManager configManager;

   private String currentUsername;



   public int bizMethod() {
      // Think about testing
      // TODO keep loaded config in ConfigManager field
      // TODO reuse ConfigManager instance in field (performance)
      // TODO push life mgmt to ConfigManager (getInstance)
      // TODO Test it
      // TODO Introduce ServiceLocator
      String config = configManager.getConfig();
      if (config.equals("NOOP")) {
         return -1;
      }
      return 0;
   }
}


@Service
class ConfigManager {
//   private static ConfigManager INSTANCE;
//   public static ConfigManager getInstance() {
//      if (INSTANCE == null) {
//         INSTANCE = new ConfigManager();
//      }
//      return INSTANCE;
//   }


   private final String config;

   private ConfigManager() {
      try (Reader reader = new FileReader("f.txt")) {
         config = IOUtils.toString(reader); // takes time
         sleepq(1000);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

//   @Cacheable("din-cache")
   public String getConfig() {
      return config;
   }
}

//class ServiceLocator {
//   private static final Map<Class<?>, Object> singletons = new HashMap<>();
//
//   public static <T> T getInstance(Class<T> aClass) {
//      return (T) singletons.computeIfAbsent(aClass,
//          Unchecked.function(Class::newInstance));
//   }
//   public static <T> void setTestInstance(Class<T> aClass, T testDouble) {
//      singletons.put(aClass, testDouble);
//   }
//}
