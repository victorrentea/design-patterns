package victor.training.oo.solid;

import victor.training.oo.structural.facade.entity.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LiskovViolationInJDK {
   public static void main(String[] args) {

      List<Customer> list = new ArrayList<>();
      list.add(new Customer());

      List<Customer> readOnly = Collections.unmodifiableList(list);

      met(readOnly);
      System.out.println(list);
   }

//   private static void met(List<? extends Customer> list) {
//      list.add(new Customer());
   private static void met(Iterable<Customer> list) {
//      list.add(new Customer());
      for (Customer s : list) {
         System.out.println(s);
      }
   }
}
