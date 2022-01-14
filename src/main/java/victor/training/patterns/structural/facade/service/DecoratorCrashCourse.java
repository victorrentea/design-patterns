package victor.training.patterns.structural.facade.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecoratorCrashCourse {
   public static void main(String[] args) {
      List<String> list = new ArrayList<>();
      List<String> list2 = Collections.unmodifiableList(list);
//new FileWriter("a");
      innocentDev(list2);
   }

   private static void innocentDev(List<String> list2) {
      list2.add("ME");
   }
}


class Child {
   public void goOut() {
      if (!mamaApproval()) {
         throw new IllegalArgumentException("No money;");
      }
      System.out.println("DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK DRINK ");
   }

   public boolean mamaApproval() {
      return false;
   }
}


///  dark corner of your code base


class FreeChild extends Child {
   @Override
   public boolean mamaApproval() {
      return true;
   }
}
