package victor.training.patterns.creational.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServiceLocatorQuick {
}


@Service
@RequiredArgsConstructor
class A {
   private final B b; // A proxy to B
   private final B b1; // A proxy to B
   private final B b2; // A proxy to B
   private final B b3; // A proxy to B
   private final B b4; // A proxy to B
   private List<Integer> list;
   private ArrayList<Integer> list2;


   public void method() {
      // serious logic
      B b = new B();
//      B b = ServiceLocator.getInstance(B.class);

      list = new LinkedList<>();

      b.methodOfCourseF6();


   }
}

@Service
class B {
   //   @PreAuthorized()
//   @Transactional
//   @Cacheable()
   public void methodOfCourseF6() {
      //jungle over here
   }
}