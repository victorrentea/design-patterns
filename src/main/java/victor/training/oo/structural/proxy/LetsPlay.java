package victor.training.oo.structural.proxy;

public class LetsPlay {

    public static void main(String[] args) {
        IMatematika realImpl = new Matematika();

//        MethodInterceptor handler = new MethodInterceptor() {
//            @Override
//            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//                System.out.println("DS: Are you calling "  + method.getName() + " with args " + Arrays.toString(args));
//                return method.invoke(realImpl, args);
//            }
//        };
//        Matematika m = (Matematika) Enhancer.create(Matematika.class, handler);


        bizLogic(new MatematikaWithLogging());
//        bizLogic(new Matematika(){
//            @Override
//            public int suma(int a, int b) {
//                System.out.println("Meeeee too!");
//                return super.suma(a, b);
//            }
//        });
    }

    private static void bizLogic(IMatematika m) {
        System.out.println("What the heck are you ? " + m.getClass());
        System.out.println(m.suma(1,1));
        System.out.println(m.suma(2,0));
        System.out.println(m.suma(3,-1));
        System.out.println(m.suma(3,1));
        System.out.println(m.proizvedenie(3,2));
    }
}


class Matematika implements IMatematika {
    @Override
    public int suma(int a, int b) {
        return a+b;
    }
    @Override
    public int proizvedenie(int a, int b) {
        return a*b;
    }
}

