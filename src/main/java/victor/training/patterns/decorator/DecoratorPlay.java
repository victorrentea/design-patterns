package victor.training.patterns.decorator;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class DecoratorPlay {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        Immutable i = new Immutable("name", ImmutableList.copyOf(arr));

        for (String phone : i.getPhones()) {
            System.out.println("Phone:  " + phone);
        }
//        i.getPhones().contains();

        i.getPhones().add("Oups!");

    }
}
interface Never {
    default void f() {
        System.out.println();
    }
}

class Sub implements Never {
}



class Mother {
    public boolean allowsDrinking() {
        return givesMoney();
    }

//    open virtual
    public boolean givesMoney() {
        x.method();
        return false;
    }
    IX x;
}

class X implements IX {
    @Override
    public void method() {

    }
}

class Student extends Mother {
    public void drink() {
        if (allowsDrinking()) System.out.println("drink");
    }
    @Override // java sucks
    public boolean givesMoney() {
        return true;
    }
}

class Immutable{
    private final String name;
    private final ImmutableList<String> phones;

    Immutable(String name, ImmutableList<String> phones) {
        this.name = name;
//        this.phones = List.copyOf(phones); // Java 11+ List.of = + malloc
//        this.phones = Collections.unmodifiableList(phones);
        this.phones = phones;// #3 ImmutableList (guava)
    }
//    public Iterable<String> getPhones() { // dont do this; will freak people, you loose some operations eg contains
    public List<String> getPhones() {
//        return new ArrayList<>(phones); // #1 most common, but worse : malloc
        return phones; // #2 decorate the list: return another implem of the List interface, that blocks some of the calls.
    }

    public String getName() {
        return name;
    }
}