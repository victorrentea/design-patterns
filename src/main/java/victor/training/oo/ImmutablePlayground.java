package victor.training.oo;

import java.util.*;

public class ImmutablePlayground {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        A a = new A(1, list);
//        a.getList().add(123);
        list.add(1);
        System.out.println(a.getList());


        Set<Child> myChildren = new HashSet<>();

        Child emma = new Child("Emma");
        System.out.println("hash(Emma)=" + emma.hashCode());
        myChildren.add(emma);
        System.out.println(myChildren.contains(emma));

//        emma.setName("Emma-Simona");
        System.out.println("hash(Emma)=" + emma.hashCode());
        System.out.println(myChildren.contains(emma));

    }
}

class Child {
    private final String name;

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(name, child.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


class B {
    int x;

    public B setX(int x) {
        this.x = x;
        return this;
    }

    public int getX() {
        return x;
    }
}
class A {
    private final int x;
    private final List<Integer> list;

    public A(int x, List<Integer> list) {
        this.x = x;
        this.list = new ArrayList<>(list);
    }

    public int getX() {
        return x;
    }
    public A setX(int newX) {
        return new A(newX, list);
    }
    public void addSomeInt(int n) {
        //some checks firs
        // JPA 2 way relationships taken care here

        list.add(n);
    }

    public List<Integer> getList() {
        return Collections.unmodifiableList(list);
    }
}
