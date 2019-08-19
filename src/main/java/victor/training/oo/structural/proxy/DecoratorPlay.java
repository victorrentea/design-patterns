package victor.training.oo.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecoratorPlay {
    public static void main(String[] args) {
        IMathematics math = new Mathematics();
        math = new MathematicsWithLogging(math);
        BizLogic logic = new BizLogic(math);
        logic.m();
    }
}

// Dear user: please don;t forget to set the math field.
// Hope anyone ever reads this.
class BizLogic {
    private final IMathematics math;

    BizLogic(IMathematics math) {
        this.math = math;
    }

    public void m() {
        System.out.println(math.sum(1,1));
    }
}

interface IMathematics {
    Integer sum(int a, int b);
}

class MathematicsWithLogging implements IMathematics{
    private static final Logger log = LoggerFactory.getLogger(MathematicsWithLogging.class);
    private final IMathematics delegate;

    public MathematicsWithLogging(IMathematics delegate) {
        this.delegate = delegate;
    }

    @Override
    public Integer sum(int a, int b) {
        log.debug("Sum("+a+","+b+")");
        return delegate.sum(a,b);
    }
}

class Mathematics implements IMathematics{
    public Integer sum(int a, int b) {
        return a + b;
    }
}