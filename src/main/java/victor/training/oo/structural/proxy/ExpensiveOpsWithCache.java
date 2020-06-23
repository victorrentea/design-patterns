//package victor.training.oo.structural.proxy;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Primary
//@Profile("with-cache")
//public class ExpensiveOpsWithCache implements IExpensiveOps {
//   private final IExpensiveOps ops;
//   private Map<Integer, Boolean> cache = new HashMap<>();
//
//   public ExpensiveOpsWithCache(IExpensiveOps ops) {
//      this.ops = ops;
//   }
//
//   public Boolean isPrime(int n) {
//      return cache.computeIfAbsent(n, ops::isPrime);
//   }
//
//   @Override
//   public String hashAllFiles(File folder) {
//      return ops.hashAllFiles(folder);
//   }
//}
