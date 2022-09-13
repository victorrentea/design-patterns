package victor.training.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Immutabil {

    private final String name;
    private final List<String> phones;

    public Immutabil(String name, List<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    public List<String> getPhones() {
        return Collections.unmodifiableList(phones);
    }
}

class Play {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Immutabil v = new Immutabil("a", list);
        v.getPhones().add("phone");

    }
}
