package victor.training.oo.creational;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ClasaNoua {


    static {
        ClasaNoua noua = new ClasaNoua()
                .setS("s")
                .setB("b")
                .addLabels("a","b");
    }
    private String s;
    private String b;
    private List<String> labels = new ArrayList<>();

    public List<String> getLabels() {
        return unmodifiableList(labels);
    }

    public ClasaNoua addLabels(String... labels) {
        this.labels.addAll(Arrays.asList(labels));
//        label.setParent(this);
        return this;
    }

    public String getS() {
        return s;
    }

    public ClasaNoua setS(String s) {
        this.s = s;
        return this;
    }

    public String getB() {
        return b;
    }

    public ClasaNoua setB(String b) {
        this.b = b;
        return this;
    }
}
