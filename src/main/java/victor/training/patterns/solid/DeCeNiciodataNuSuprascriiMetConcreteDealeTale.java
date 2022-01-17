package victor.training.patterns.solid;

public class DeCeNiciodataNuSuprascriiMetConcreteDealeTale {


}


class Adolescent {
   public void beu() {
      if (mamaDaBani()) {
         System.out.println("beu");
      }
   }

   public boolean mamaDaBani() {
      return false;
   }
}

///////----------------

class AdolescentEmancipat extends Adolescent {

   @Override
   public boolean mamaDaBani() {
      return true;
   }
}