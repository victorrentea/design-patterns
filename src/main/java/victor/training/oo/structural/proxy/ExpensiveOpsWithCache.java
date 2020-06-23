package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExpensiveOpsWithCache implements IExpensiveOps {
   private final IExpensiveOps ops;
   private Map<Integer, Boolean> cache = new HashMap<>();

   public ExpensiveOpsWithCache(IExpensiveOps ops) {
      this.ops = ops;
   }

   public Boolean isPrime(int n) {
      return cache.computeIfAbsent(n, ops::isPrime);
   }

   @Override
   public String hashAllFiles(File folder) {
      return ops.hashAllFiles(folder);
   }
}
