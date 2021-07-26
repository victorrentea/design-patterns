package victor.training.patterns.creational.singletonnew;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class XTest {
   @Mock
   private SomeRepo s;
   @InjectMocks
   private X x;

   @Test
   public void test() {
      // arrangeD
      Mockito.when(s.deInstanta()).thenReturn(1);

      // act
      int actual = x.methodX();

      // assert
      assertEquals(2, actual);
      Mockito.verify(s).saveCevaCritic("aa");

   }
}