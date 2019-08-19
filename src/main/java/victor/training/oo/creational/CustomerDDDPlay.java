package victor.training.oo.creational;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.isBlank;

public class CustomerDDDPlay {
    public static void main(String[] args) {
        new Customer("a").setName(null); // Sir Charles Anthony Richards aka "Tony Hoare"
    }
}


class Customer {
    private String name;
    private String nin; //also required? => add it to the constructor the same way as name

    public Customer(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("Empty name");
        }
        this.name = name;
        return this;
    }
}