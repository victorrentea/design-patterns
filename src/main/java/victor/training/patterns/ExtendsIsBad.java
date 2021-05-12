package victor.training.patterns;

public class ExtendsIsBad {
   public static void main(String[] args) {
      new Copil().mergeLaBar();
   }
}

class Tata {
   //   Mama mama;
   public void protofel() {
      if (!mamaDaVoie()) {
         throw new IllegalArgumentException();
      }
   }

   public boolean mamaDaVoie() {
      return false;
   }
}

class Copil extends Tata {
   public void mergeLaBar() {
      protofel();
   }

   @Override
   public boolean mamaDaVoie() {
      return true;
   }
}

interface Mama {
   boolean daVoie();
}