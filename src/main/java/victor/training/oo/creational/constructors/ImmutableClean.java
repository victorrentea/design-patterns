package victor.training.oo.creational.constructors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmutableClean {
   public static void main(String[] args) {
      List<String> strings = new ArrayList<>();
      A a = new A("a", new B(1), strings);

      System.out.println(a.getStrings());
//      a.getStrings().add("Hop si eu dupa");
      a.getStrings().containsAll(Arrays.asList("A","B"));
      for (String string : a.getStrings()) {
         System.out.println("elem : " + string);
      }
      System.out.println(a.getStrings());

      A aDarCuAltS = a.setS("newS");

      BigDecimal two = BigDecimal.ONE.add(BigDecimal.ONE);
   }
}
//@Entity
class AEntity { //mutable, getters and setters
//   @Id
   private Long id;

//   @Embedded
   private A a;

}

//@Embeddable
class A {
   private /*final*/ String s;
   private /*final*/ B b;
   private /*final*/ List<String> strings;
   protected A() {} // pt ochii lui Hibernate
   public A(String s, B b, List<String> strings) {
      this.s = s;
      this.b = b;
      this.strings = new ArrayList<>(strings);
   }

   public List<? extends String> getStrings() {
      return strings;
   }
   //riscant: afli de exceptie la Runtime (prea tarziu)
//   public List<String> getStrings() {
//      return Collections.unmodifiableList(strings);

//   }
   // permite doar for
//   public Iterable<String> getStrings() {
//      return strings;

//   }


   public String getS() {
      return s;
   }

   public B getB() {
      return b;
   }

   public A setS(String newS) {
      return new A(newS, b, strings);
   }
}

class B {
   private final int i;
   public B(int i) {
      this.i = i;
   }

   public int getI() {
      return i;
   }
}
