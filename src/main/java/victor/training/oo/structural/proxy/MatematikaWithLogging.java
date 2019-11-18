package victor.training.oo.structural.proxy;

public class MatematikaWithLogging implements  IMatematika {
    private final IMatematika delegate;
    public MatematikaWithLogging(IMatematika delegate) {
        this.delegate = delegate;
    }

    @Override
    public int suma(int a, int b) {
        System.out.println("DS here ");
        return delegate.suma(a,b);
    }

    @Override
    public int proizvedenie(int a, int b) {
        System.out.println("DS here ");
        return delegate.proizvedenie(a,b);
    }
}
