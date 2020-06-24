package victor.training.oo.structural.proxy;

import java.io.File;

public interface IExpensiveOps {

//   @LoggedMethod -- pare ca nu merge
   Boolean isPrime(int n);

   String hashAllFiles(File folder);

   void killFolderCache(File file);
}
