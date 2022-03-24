package victor.training.patterns;

import java.util.Arrays;
import java.util.List;

public class WhyShouldWeForgetAboutExtends {
   public static void main(String[] args) {
      List<Mother> mothers = Arrays.asList(new Mother(), new StudentCS()); // WOT?
      new StudentCS().goToBar();
   }
}

class Mother {
   public boolean allowToGoToBar() {
      return false;
   }

   public void wallet() {
      System.out.println("Money");
   }
}

class StudentCS extends Mother {
   private Mother mother;

   public void goToBar() {
      if (allowToGoToBar()) {
         wallet();
      }
   }

   @Override
   public boolean allowToGoToBar() {
      return true;
   }
}