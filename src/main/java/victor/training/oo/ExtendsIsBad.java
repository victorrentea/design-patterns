package victor.training.oo;

public class ExtendsIsBad {
}



class Parent {
   public void portofel() {
      if (!intreabaMama()) {
         throw new IllegalArgumentException();
      }
   }
   public boolean intreabaMama() {
      return false;
   }
}

class Child extends  Parent {
   public void mergiLaPUB() {
       portofel();
   }

   @Override
   public boolean intreabaMama() {
      return true;
   }
}