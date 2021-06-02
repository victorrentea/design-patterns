package victor.training.patterns.creational.singleton;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ATest {
   @Test
   public void test() {
      B bMock = Mockito.mock(B.class);
      when(bMock.someMethodYouDontCareAbout(anyInt()))
          .thenReturn(5);
      // or equivalent:
      bMock = new B() {
         @Override
         public int someMethodYouDontCareAbout(int i) {
            return 5;
         }
      };

      A a = new A(bMock);

      int result = a.methodToTest();// prod call

      assertThat(result).isEqualTo(10);
   }

}