package victor.training.oo.creational.singleton;

import org.apache.commons.lang.NotImplementedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestingWithASingleton {

    @Mock
    ConfigSingleton s;// = ConfigSingleton.getInstance();
    @Test
    public void testingWithASingleton() {
//        Mockito.mock()
        when(s.getConfig()).thenReturn("a");

        Assert.assertEquals("A", new BizLogic(s).m());
    }
}

class BizLogic {
    private final ConfigSingleton s;// = ConfigSingleton.getInstance();

    BizLogic(ConfigSingleton s) {
        this.s = s;
    }

    public String m() {
        return s.getConfig().toUpperCase();
    }
}

//@Service
class ConfigSingleton {
//    private static ConfigSingleton INSTANCE;
//
//    private ConfigSingleton() {
//    config=initialize();
//    }
//
//    public static ConfigSingleton getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ConfigSingleton();
//        }
//        return INSTANCE;
//    }

    public String getConfig() {
        throw new NotImplementedException("I don't want to really cal this from my tests");
    }
}
