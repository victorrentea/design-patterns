package victor.training.patterns.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.patterns.singleton.BizService;
import victor.training.patterns.singleton.ConfigManager;

@ExtendWith(MockitoExtension.class)
public class BizService5Test {
   @Test
   public void test() {
      try (MockedStatic<ConfigManager> mock = Mockito.mockStatic(ConfigManager.class)) {
         ConfigManager mockManager = Mockito.mock(ConfigManager.class);
//         mock.when(ConfigManager::getInstance).thenReturn(mockManager);
         Mockito.when(mockManager.getConfig()).thenReturn("NOOP");

         Assertions.assertEquals(-1, new BizService().bizMethod());
      }
   }
}
