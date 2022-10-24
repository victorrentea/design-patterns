package victor.training.patterns.proxy;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HowImutable {
    private final String x;
    private final List<String> list;

    public HowImutable(String x, List<String> list) {
        this.x = x;
        this.list = list;
    }

    public String getX() {
        return x;
    }

    public List<String> getList() {
//        return new ArrayList<>(list);
        return Collections.unmodifiableList(list); // static facytory method returning a
        // certain type of decorator over the original list. mem efficient.

//        return new UnmodifiableRandomAccessList(list);
    }

    @Override
    public String toString() {
        return "HowImutable{" +
               "x='" + x + '\'' +
               ", list=" + list +
               '}';
    }
}

class Play {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        HowImutable imm = new HowImutable("a", list);

        System.out.println(imm.getList());

        // break liskov ? because performacne. and because J uscks.
        // what in the world told the client that [s]he is not allowed to call add()??
        // runtime exception !!!
        imm.getList().add("Surprise!!!");



    }
}

