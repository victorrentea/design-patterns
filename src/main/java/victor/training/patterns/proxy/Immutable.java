package victor.training.patterns.proxy;

import com.google.common.collect.ImmutableList;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Immutable {
    private final String s;
    private final ImmutableList<String> list;

    public Immutable(String s, ImmutableList<String> list) {
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

    public ImmutableList<String> getList() {
//        return new ArrayList<>(list); // - malloc / gc
//        return Collections.unmodifiableList(list); // decorator in disguise. + no malloc of the entire array
        // returned by a static facytory method that hides from me
        // the actual type that I am returned
        return list;
    }
}


class Play {
    public static void main(String[] args) {
        ImmutableList<String> list = ImmutableList.of("1");
        Immutable obj = new Immutable("a", list);
        System.out.println("Before: " + obj);

        // --- can I change obj' state here?
        obj.getList().add("dirty hack"); // BETTER: RUNTIME ERROR, not a silent ignore of the add


        System.out.println("After: " + obj);
    }
}