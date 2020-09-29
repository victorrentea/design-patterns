package victor.training.patterns.creational.singleton;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class SingletonIntro {
}

class BizService {
   public int bizMethod() {
      // Think about testing
      // TODO reuse B instance (performance)
      // TODO push life mgmt to B (getInstance)
      // TODO Test it
      String config = ConfigManager.getInstance().getConfig();
      if (config.equals("NOOP")) {
         return -1;
      }
      return 0;
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