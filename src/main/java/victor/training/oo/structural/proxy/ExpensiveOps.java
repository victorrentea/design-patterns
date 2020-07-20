package victor.training.oo.structural.proxy;

import lombok.SneakyThrows;

import java.io.File;

public interface ExpensiveOps {
   Boolean isPrime(int n);

   @SneakyThrows
   String hashAllFiles(File folder);
}
