package victor.training.patterns.proxy;

import com.google.common.collect.ImmutableList;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
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
        return Collections.unmodifiableList(list); // violates Liskov Substitution Principle
    }

    // guava is today the most used library in java.
//    public ImmutableList<String> getList2() {
//        return Collections.unmodifiableList(list); // violates Liskov Substitution Principle
//    }
}

class Play {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Immutable obj = new Immutable("a", list);
        System.out.println("Before: " + obj);

        // --- can I change object's state here?
        obj.getList().add("b"); // > in Kotlin this does not compile. To compile the returbn type should be MutableList extends List
//        obj.getList2().add("b"); // >compiler warning

        System.out.println("After: " + obj);
    }
}