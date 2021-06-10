package victor.training.patterns.creational.factory;

//@Ser
public class DontUseMe {

   private static DontUseMe INSTANCE;

   private DontUseMe() {

   }

   public synchronized static DontUseMe getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new DontUseMe();
      }
      return INSTANCE;
   }
}
