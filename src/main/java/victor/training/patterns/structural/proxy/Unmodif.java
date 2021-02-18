package victor.training.patterns.structural.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Unmodif {

   public static void main(String[] args) {
      List<String> list = f();
      list.add("a");
   }

   private static List<String> f() {
      List<String> list = new ArrayList<>();

      list.add("a");
      System.out.println(list);

      list = Collections.unmodifiableList(list); // polimorfism imi intoarce o alta implem decat ArrayList
      // care blocheaza orice incercare de modificare

      System.out.println(list);
      return list;
   }
}
