package victor.training.oo.creational.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuilderiPragmatici {
    public static void main(String[] args) {
        EntitateaMea e = new EntitateaMea()
                .setAddresss("addr")
                .addPhone("telefon")
                .setName("name");

        EntitateaMea e2 = new EntitateaMea();
        System.out.println(e2.getName());
    }
}

class EntitateaMea {
    private String name;
    private String address;
    private List<String> phones = new ArrayList<>();

    public EntitateaMea(String address) {
        setAddress(address);
    }

    public EntitateaMea setAddress(String address) {
        Objects.nonNull(address);
        this.address = address;
        return this;
    }

    public EntitateaMea addPhone(String phone) {
        phones.add(phone);
        return this;
    }

    public String getName() {
        return name;
    }

    public EntitateaMea setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddresss() {
        return addresss;
    }

    public EntitateaMea setAddresss(String addresss) {
        this.addresss = addresss;
        return this;
    }
}