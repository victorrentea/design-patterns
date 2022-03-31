package victor.training.patterns.structural.proxy;

public abstract class Template {
   private Pas2 pas2;

   public void mareComplex() {
      pas2.execute();
      pas();
   }

   protected abstract void pas() ;
}

interface Pas2 {
   public void execute();
}
class DefaultPas2 implements Pas2{
   @Override
   public void execute() {
      System.out.println("Standard");
   }
}
class Custom implements Pas2 {
   @Override
   public void execute() {
      System.out.println("te joc");
   }
}






class Imple extends Template {

   @Override
   protected void pas() {

   }


}
