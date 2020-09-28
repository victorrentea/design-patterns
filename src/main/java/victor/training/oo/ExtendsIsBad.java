package victor.training.oo;

public class ExtendsIsBad{
   public static void main(String[] args) {
      new Copil().mergiLaCarciuma();
   }


}


class Tata {
   public int portofel() {
      if (!mamaDaVoie()) {
         throw new IllegalArgumentException();
      }
      return 999;
   }

   public boolean mamaDaVoie() {
      return false;
   }
}

class Copil extends Tata {

   public void mergiLaCarciuma() {
      System.out.println("Beu de " + portofel());
   }

   @Override
   public boolean mamaDaVoie() {
      return true;
   }
}