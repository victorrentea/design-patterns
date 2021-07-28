package victor.training.patterns.behavioral.observer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class A {
   private final String prop;

   public A(@Value("${din.fisier:default}") String prop) {
      this.prop = prop;
   }

   @PostConstruct
   public void method() {
      System.out.println(prop);
   }

}

class DiNtest {
   public void method() {
      new A("VALOARE DIN TEST");
   }
}