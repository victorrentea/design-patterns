package victor.training.patterns.singleton;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static victor.training.patterns.util.ThreadUtils.sleepq;

@Slf4j
public class SingletonBasics {
   public static void main(String[] args) {
      log.debug("Start");
      log.debug("Out: " + new BizService().bizMethod());
      log.debug("Out: " + new BizService().bizMethod());
   }
}

class BizService {
   public int bizMethod() {
      // TODO keep loaded config in ConfigManager field
      // TODO reuse ConfigManager instance in field (performance)
      // TODO push life mgmt to ConfigManager (getInstance)
      ConfigManager configManager = ConfigManager.getInstance();

      String config = configManager.getConfig();
      if (config.equals("NOOP")) {
         return -1;
      }
      return 0;
   }
}


// scop: sa opresc instantierea acestei clase de mai mult decat o singura data
// inauntru
class ConfigManager {

   private static ConfigManager instance;
   private ConfigManager() {
      config = init();
   }
   public static ConfigManager getInstance() {
      if (instance == null) {
         instance = new ConfigManager();
      }
      return instance;
   }

   private final String config;
   private String init() {
      final String config;
      try (Reader reader = new FileReader("f.txt")) {
         config = IOUtils.toString(reader); // takes time
         sleepq(1000); // ma prefac
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      return config;
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
