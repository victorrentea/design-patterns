package victor.training.patterns.proxy;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Immutable {
    private final String s;
    private final List<String> list;

    public Immutable(String s, List<String> list) {
        this.s = s;
        this.list = list;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Immutable'{'s=''{0}'', list={1}'}'", s, list);
    }

    public String getS() {
        return s;
    }

    public List<String> getList() {
        return new ArrayList<>(list); // - malloc / gc
    }
}


class Play {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Immutable obj = new Immutable("a", list);
        System.out.println("Before: " + obj);

        // --- can I change obj' state here?
        obj.getList().add("dirty hack"); // 😨 surprise!!! your add is NOT saved. hehehehe sucker!

        System.out.println("After: " + obj);
    }
}