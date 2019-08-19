package victor.training.oo.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecoratorPlay {
    public static void main(String[] args) {
        Mathematics math = new Mathematics();
        BizLogic logic = new BizLogic(math);
        logic.m();
    }
}

// Dear user: please don;t forget to set the math field.
// Hope anyone ever reads this.
class BizLogic {
    private final Mathematics math;

    BizLogic(Mathematics math) {
        this.math = math;
    }

    public void m() {
        System.out.println(math.sum(1,1));
    }
}


class Mathematics {
    public Integer sum(int a, int b) {
        return a + b;
    }
    public int product(int a, int b) {
        return a * b;
    }
}