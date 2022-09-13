package victor.training.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Imutabile {
    private final String name;
    private final List<String> phones;

    public Imutabile(String name, List<String> phones) {
        this.name = name;
        this.phones = phones;
    }
    public List<String> getPhones() {
        return Collections.unmodifiableList(phones);
    }
    public String getName() {
        return name;
    }
}

class JavaENaspa95 {
    public static void main(String[] args) {
        List<String> phones = new ArrayList<>();
        Imutabile cica = new Imutabile("num e", phones);

        cica.getPhones().add("#sieu");
    }
}
