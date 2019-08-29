package victor.training.oo.creational.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class InjectareInSingletonOldSchool {
}


//@Service
//class Freddy {
//    private static Freddy INSTANCE = new Freddy();
//    public static Freddy getInstance() {
//        return INSTANCE;
//    }
//
//    @Autowired
//    private Alta alta;
//}
//
@Service
class Alta{}

abstract class AbstractExporter {
    @Autowired Alta alta;
    public void export() {
        writeItem();
    }

    protected abstract void writeItem();
}

@Service
class CsvExporter extends  AbstractExporter {
    protected void writeItem() {
    }
}

@Service
class ExcelExporter extends AbstractExporter {
    protected void writeItem() {

    }
}