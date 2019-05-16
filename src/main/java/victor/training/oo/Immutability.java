package victor.training.oo;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Immutability {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b"));
        OtherClass c = new OtherClass();
        Immutable i = new Immutable("a", list, c);
//            i.getList().add("new");
        list.add("new");
        c.setS("suprise!!!");
    }
}
class OtherClass {
    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}

class Immutable {
    private final String s;
    private final List<String> list;
    private final OtherClass c; // this makes my class modifiable

    public Immutable(String s, List<String> list, OtherClass c) {
        this.s = s;
        this.list = new ArrayList<>(list);
        this.c = c;
        if (StringUtils.isBlank(s)) throw new IllegalArgumentException("NO!");
    }

    public String getS() {
        return s;
    }
    public Immutable setS(String newS) {
        return new Immutable(newS, list,c);
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }


}
