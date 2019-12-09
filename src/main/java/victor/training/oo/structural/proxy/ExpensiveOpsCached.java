package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExpensiveOpsCached implements  IExpensiveOps{
    private final ExpensiveOps expensiveOps;
    private Map<Integer, Boolean> cash = new HashMap<>();

    public ExpensiveOpsCached(ExpensiveOps expensiveOps) {
        this.expensiveOps = expensiveOps;
    }


    public Boolean isPrime(int n) {
        System.out.println("SRI-ul");
        if (cash.containsKey(n)) {
            return cash.get(n);
        }
        Boolean result = expensiveOps.isPrime(n);
        cash.put(n, result);
        return result;
    }

    public String hashAllFiles(File file) {
        return expensiveOps.hashAllFiles(file);
    }
}
