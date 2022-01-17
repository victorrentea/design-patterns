package victor.training.patterns.solid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//interface MutableList extends ImmutableList {}

//interface ToataLogica {
//   // us1
//   // us2
//   // us3
//}

public class LiskovViolationInJDK {
   public static void main(String[] args) {


      List<String> list = new ArrayList<>();


      list.add("a");

      call(Collections.unmodifiableList(list));

      ObiectImutabil obiectImutabil = new ObiectImutabil("a", Arrays.asList("a"));

      obiectImutabil.getList().clear();
   }

   private static void call(List<String> list) {
      // dusmanul e aici
   }
}


class ObiectImutabil {
   private final String a;
   private final List<String> list;

   public ObiectImutabil(String a, List<String> list) {
      this.a = a;
      this.list = list;
   }

   public String getA() {
      return a;
   }

   public List<String> getList() {
      // incalca Liskov
      // Pattern1: UnmodifiableList este Decorator pattern: implem aceeasi interfata ca obj "decorat" (lista originala), dar implem met sa faca ceva diferit uneori (scrierile le blocheaza cu exception)
      // Pattern2: factory methdd
      return Collections.unmodifiableList(list);
   }
}
