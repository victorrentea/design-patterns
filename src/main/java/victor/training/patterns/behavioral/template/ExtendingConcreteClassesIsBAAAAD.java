package victor.training.patterns.behavioral.template;

public class ExtendingConcreteClassesIsBAAAAD {
   public static void main(String[] args) {

      new CSHacker().drink();
   }
}


class Student {
   public void drink() {
      if (mamaGIvesMoney()) {
         System.out.println("drink crafted beer");
      }
   }

   public boolean mamaGIvesMoney() {
      return false;
   }

   protected void inheritedMEthod() {

   }
}

class CSHacker extends Student {
   @Override
   public boolean mamaGIvesMoney() {
      return true;
   }

   public void method() {
      inheritedMEthod();
   }

}