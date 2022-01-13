package victor.training.patterns.structural.facade.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecoratorCrashCourse {
   public static void main(String[] args) {

      List<String> list = new ArrayList<>();
      List<String> list2 = Collections.unmodifiableList(list);
//new FileWriter("a");
      list2.add("ME");
   }
}
