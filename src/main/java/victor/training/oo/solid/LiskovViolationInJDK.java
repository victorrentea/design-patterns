package victor.training.oo.solid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiskovViolationInJDK {
   public static void main(String[] args) {

      List<String> list = new ArrayList<>();
      list.add("a");
      list.add("b");

      List<String> readOnly = Collections.unmodifiableList(list);

      met(readOnly);
      System.out.println(list);
   }

   private static void met(List<String> list) {
      list.add("c");
   }
}
