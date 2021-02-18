package victor.training.patterns.structural.proxy;

public class AltDecorator implements IExpensiveOps {
   private final IExpensiveOps expensiveOps;

   public AltDecorator(IExpensiveOps expensiveOps) {
      this.expensiveOps = expensiveOps;
   }

   @Override
   public Boolean isPrime(int n) {
      Boolean v = null;
      try {
//      begin Tx
         v = expensiveOps.isPrime(n);
//      commit Tx
      } catch (Exception e) {
         //rollback Tx
      }
      return v;
   }
}
