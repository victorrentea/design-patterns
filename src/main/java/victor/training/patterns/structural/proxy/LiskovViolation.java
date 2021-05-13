package victor.training.patterns.structural.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiskovViolation {
   public static void main(String[] args) {


      List<Long> numbers = new ArrayList<>();

      numbers = Collections.unmodifiableList(numbers);
      numbers.clear();
   }
}
