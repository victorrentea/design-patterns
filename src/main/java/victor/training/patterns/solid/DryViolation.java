package victor.training.patterns.solid;

public class DryViolation {
   public void method() {
      aa(false);
   }

   public void method2() {
      aa(true);
   }

   private void aa(boolean forMyMethod2) {
      oneMoreIntermediary(forMyMethod2);
   }

   private void oneMoreIntermediary(boolean forMyMethod2) {
      aaStart();
      if (forMyMethod2) {
         System.out.println("Stuff for method2");
      }
      aaEnd();
   }

   private void aaEnd() {
      System.out.println("a");
      System.out.println("a");
      System.out.println("acc");
      System.out.println("a");
   }

   private void aaStart() {
      System.out.println("a");
      System.out.println("abb");
      System.out.println("a");
      System.out.println("a");
   }
}
