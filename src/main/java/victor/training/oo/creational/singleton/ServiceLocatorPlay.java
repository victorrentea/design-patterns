package victor.training.oo.creational.singleton;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public class ServiceLocatorPlay {
//    {
//        //din teste
//        ServiceLocator.replaceBClass(B.class, mock);
//    }
}

@Service
@RequiredArgsConstructor
class A {
    private final B b;

    public void m() {
        int x = b.met();
    }
}
class B {
    private C c;

    public int met() {

        return -1;
    }
}
class C {

}