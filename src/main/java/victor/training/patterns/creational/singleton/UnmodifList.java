package victor.training.patterns.creational.singleton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnmodifList {


   public static void main(String[] args) {

      List<Integer> numbers = IntStream.range(1, 10).boxed().collect(Collectors.toList());

      m(Collections.unmodifiableList(numbers));
   }

   private static void m(List<Integer> numbers) {
      numbers.clear();
   }
}
