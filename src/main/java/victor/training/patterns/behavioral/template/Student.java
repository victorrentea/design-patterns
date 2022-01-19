package victor.training.patterns.behavioral.template;

public class Student {
   public static void main(String[] args) {

      new Hacker().bea();
   }

   public void bea() {
      if (mamaDaBani()) {
         System.out.println("BEU");
      }
   }

   public void mostenita() {

   }

   public boolean mamaDaBani() {
      return false;
   }
}


class Hacker extends Student {
   public boolean mamaDaBani() {
      mostenita();
      return true;
   }
}
