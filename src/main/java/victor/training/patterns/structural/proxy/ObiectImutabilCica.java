package victor.training.patterns.structural.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObiectImutabilCica {
   private final String name;
   private final List<String> phones;

   public ObiectImutabilCica(String name, List<String> phones) {
      this.name = name;
      this.phones = phones;
   }

   public String getName() {
      return name;
   }

   public List<String> getPhones() {
      return Collections.unmodifiableList(phones);
   }
}

class CinevaRau {
   public static void main(String[] args) {

      ObiectImutabilCica obj = new ObiectImutabilCica("a", new ArrayList<>());
      obj.getPhones().clear();
   }
}