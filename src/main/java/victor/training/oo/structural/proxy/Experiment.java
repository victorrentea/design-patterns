package victor.training.oo.structural.proxy;

public class Experiment {
    public static void main(String[] args) {

        Mate mate = new MateReal();
        holyBizLogic(new MateLogat(mate));
    }

    public static void holyBizLogic(Mate mate) {
        System.out.println(mate.sum(1, 1));
    }
}
interface Mate {
    Integer sum(int a, int b);
}
class MateLogat implements Mate{
    private final Mate mateDelegate;

    public MateLogat(Mate mateReal) {
        this.mateDelegate = mateReal;
    }
    public Integer sum(int a, int b) {
        System.out.println("LOG: Suma intre " + a + " si " + b);
        return mateDelegate.sum(a,b);
    }
}


class MateReal implements Mate{
    public Integer sum(int a, int b) {
        return a + b;
    }
}