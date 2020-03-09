package victor.training.oo.creational;

import org.junit.Assert;
import org.junit.Test;

public class ATest {
    @Test
    public void mFaceUppercase() {
//        RegistruDeInstante_ServiceLocator.setInstance(B.class, new BFalsa());
        A a = new A(); // se duce la registru si obt instanta falsa pe care abia ai pus-o acolo.
//        a.setB(new BFalsa());
        String actual = a.m();
        Assert.assertEquals("HELLO WORLD", actual);
    }

}

class BFalsa extends B {
    @Override
    public String metodaB() {
        return "Hello World";
    }
}
