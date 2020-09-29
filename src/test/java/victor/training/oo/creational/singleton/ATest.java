package victor.training.oo.creational.singleton;

import org.junit.Assert;
import org.junit.Test;

public class ATest {
   @Test
   public void test() {
      Assert.assertEquals(-1, new A().bizMethod());
   }
}
