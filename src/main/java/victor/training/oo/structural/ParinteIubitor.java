package victor.training.oo.structural;

import victor.training.oo.stuff.ThreadUtils;

public class ParinteIubitor {
   public static void main(String[] args) {
      Copil copil = new Copil();
      Parinte parinte = new Parinte(copil);
      parinte.vineAcasaObosit(MagazinDeJucarii.obtineJucarie(true));

//      Connection connection = DriverManager.getConnection("");
   }

}
class MagazinDeJucarii {

   public static Jucarie obtineJucarie(boolean safe) {
      if (safe) {
         return new Consola();
//         return new Papusa();
      } else {
         return new Ciocan();
      }
   }
}

interface Jucarie {

}
class Consola implements Jucarie{
}
class Ciocan implements Jucarie{
}

class Papusa implements Jucarie {

}
 class Parinte {
   private final Copil copil;

   public Parinte(Copil copil) {
      this.copil = copil;
   }

   public void vineAcasaObosit(Jucarie jucarie) {
       copil.daJucarie(jucarie);
   }


}

class Copil {

   public void daJucarie(Jucarie jucarie) {
      System.out.println("Ma joc cu " + jucarie);
      ThreadUtils.sleep(5000);

   }
}


