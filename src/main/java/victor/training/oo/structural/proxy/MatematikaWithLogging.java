package victor.training.oo.structural.proxy;

public class MatematikaWithLogging extends  Matematika{

    @Override
    public int suma(int a, int b) {
        System.out.println("DS here ");
        return super.suma(a,b);
    }

}
