package victor.training.oo.structural.proxy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@Cached
public class ExpesiveOpsWithCaching implements IExpensiveOps {
    private Map<Integer, Boolean> cache = new HashMap<>();
    private IExpensiveOps ops;

    public ExpesiveOpsWithCaching(IExpensiveOps ops) {
        this.ops = ops;
    }

    public Boolean isPrime (int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        Boolean result = ops.isPrime(n);
        cache.put(n, result);
        return result;
    }

    @Override
    public String hashAllFiles(File folder) {
        return ops.hashAllFiles(folder);
    }
}
