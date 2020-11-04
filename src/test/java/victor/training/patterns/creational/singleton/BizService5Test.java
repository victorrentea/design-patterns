package victor.training.patterns.creational.singleton;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BizService5Test {
   @Test
   public void test() {
      ConfigManager mockManager = Mockito.mock(ConfigManager.class);

      ServiceLocator.setTestInstance(ConfigManager.class, mockManager);

      Mockito.when(mockManager.getConfig()).thenReturn("NOOP");

      Assertions.assertEquals(-1, new BizService().bizMethod());
   }
}
