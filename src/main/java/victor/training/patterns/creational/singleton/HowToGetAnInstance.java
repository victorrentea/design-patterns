package victor.training.patterns.creational.singleton;

import org.springframework.stereotype.Service;

public class HowToGetAnInstance {

}

@Service
class A {
   private final B b;

   public A(B b) {
      this.b = b;
   }

   public int methodToTest() {
//      B b = ServiceLocator.getInstance(B.class); // today it's an antipattern
      int five = b.someMethodYouDontCareAbout(4);
      return five * 2;
   }

}

@Service
class B {
   public int someMethodYouDontCareAbout(int i) {
      return i - 1;
   }
}