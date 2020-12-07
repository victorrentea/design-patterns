package victor.training.patterns.creational.singleton;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@Slf4j
public class SingletonIntro {

   public static void main(String[] args) {
      log.debug("Start");
//      log.debug("Out: " + new BizService(configManager).bizMethod());
//      log.debug("Out: " + new BizService(configManager).bizMethod());
   }
}

@Service
class BizService {
   private final ConfigManager configManager;

   BizService(ConfigManager configManager) {
      this.configManager = configManager;
   }

   public int bizMethod() {
      String config = configManager.getConfig();
      if (config.equals("NOOP")) {
         return -1;
      }
      return 0;
   }
}


@Service
class ConfigManager {

   private /*static*/ String config; // practic orice variabila pe Singleton este 'effectively static'

   // DE AICI
   private static ConfigManager INSTANCE;

   public static ConfigManager getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new ConfigManager();
      }
      return INSTANCE;
   }
   // PANA AICI  nu va fi necesar in viata reala.
   // Pentru ca vor exista niste containere de Dependency Injection

   private ConfigManager() {
      try (Reader reader = new FileReader("f.txt")) {
         config = IOUtils.toString(reader); // takes time
         sleepq(1000);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

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
