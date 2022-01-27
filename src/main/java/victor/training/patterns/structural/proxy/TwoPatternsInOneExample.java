package victor.training.patterns.structural.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoPatternsInOneExample {


   public static void main(String[] args) {
      MyImmutableObjMadeWithLove obj = new MyImmutableObjMadeWithLove();

      obj.getStrings().add("aa");
   }
}

class MyImmutableObjMadeWithLove {
   List<String> strings = new ArrayList<>();

   public List<String> getStrings() {
      // 1 WHY? staticMethod vs new () because it hides what concrete TYPE you get back : (1) STATIC FACTORY METHOD
      // 2 Decorator pattern: you find an alternative implem of an interface that can WRAP around another obj of that interface type
      return Collections.unmodifiableList(strings); //
   }
}