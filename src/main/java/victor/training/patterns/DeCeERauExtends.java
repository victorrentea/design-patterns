package victor.training.patterns;

public class DeCeERauExtends {
}


class Parinte {
   public int portofel() {
      if (!intreabaMama()) {
         throw new IllegalArgumentException("N-am");
      }
      return 100; // bani
   }

   public boolean intreabaMama() {
      return false;
   }
}

class Copil extends Parinte {
   @Override
   public boolean intreabaMama() { // suprascrii metode concrete.
      return true;
   }

   public void mergLaCarciuma() {
      portofel();

   }
}