package victor.training.oo.structural.proxy;

import lombok.SneakyThrows;

import java.io.File;

public interface IExpensiveOps {
    Boolean isPrime(int n);

    String hashAllFiles(File folder);
}
