package victor.training.patterns.creational.singleton;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
//@PrepareForTest(ConfigManager.class)
public class BizServiceTest {
   @Test
   public void test() {
//      PowerMockito.mockStatic(ConfigManager.class);

      ConfigManager mockManager = Mockito.mock(ConfigManager.class);
//      Mockito.when(ConfigManager.getInstance()).thenReturn(mockManager);
      Mockito.when(mockManager.getConfig()).thenReturn("NOOP");
      ServiceLocator.setTestInstance(ConfigManager.class, mockManager);

      Assert.assertEquals(-1, new BizService().bizMethod());
   }
}
