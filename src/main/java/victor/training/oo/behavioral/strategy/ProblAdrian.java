//package victor.training.oo.behavioral.strategy;
//
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
//
//public class ProblAdrian {
//
//
//}
//
//abstract class I {
//    public abstract void populate();
//
//    public abstract void process();
//
//    public void altaFunctie() {
//    }
//}
//
//class A implements I {
//    private String s;
//
//    public void populate() {
//
//    }
//
//    public void process() {
//
//    }
//
//    public void altaFunctie() {
////        throw  new NotImplementedException();
//        // THIS LOGIC DOES NOT APPLY
//    }
//}
//
//
//
//class B implements I {
//    private int i;
//    private boolean ceva;
//
//    public void populate() {
//
//    }
//
//    public void process() {
//        if (ceva) {
//            altaFunctie();
//        }
//    }
//
//    @Override
//    public void altaFunctie() {
//        //tras de par
//    }
//}
//class BExtra {
//    public void altaFunctie() {
////        throw  new NotImplementedException();
//        // THIS LOGIC DOES NOT APPLY
//    }
//}
//class PriceAlgo {
//    public void m(I i) {
//        //logica comuna
//
//        i.populate();
//
//        i.process();
//
//        if (ceva) {
//            i.altaFunctie();
//        }
//        //un pas doar pentru UK
//
//        // logic comuna
//    }
//}