package victor.training.patterns.behavioral.strategy;

public class BuildersAreAMistaks {


   public static void main(String[] args) {
      new SomeImmutable("a","b","c")
          .withIntegers(new Integers(1,1,1,1,1,1,1));

          //get te
   }
}

class Integers {
   private final Integer a1;
   private final Integer a2;
   private final Integer a3;
   private final Integer a4;
   private final Integer a5;
   private final Integer a6;
   private final Integer a7;

   Integers(Integer a1, Integer a2, Integer a3, Integer a4, Integer a5, Integer a6, Integer a7) {
      this.a1 = a1;
      this.a2 = a2;
      this.a3 = a3;
      this.a4 = a4;
      this.a5 = a5;
      this.a6 = a6;
      this.a7 = a7;
   }
}


class SomeImmutable {
   private final String a,b,c;
   private final Integers integers;

   SomeImmutable(String a, String b, String c) {
      this(a,b,c,null);
   }
   SomeImmutable(String a, String b, String c, Integers integers) { // canonical constructor
      this.a = a;
      this.b = b;
      this.c = c;
      this.integers = integers;
   }


   public SomeImmutable withIntegers(Integers integers) {
         return new SomeImmutable(a,b,c,integers);
   }
}