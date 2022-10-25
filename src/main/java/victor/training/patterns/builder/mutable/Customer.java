package victor.training.patterns.builder.mutable;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Customer {
    private String name;
    private List<String> labels = new ArrayList<>();
    private Address address;

    // generated setters still return "this": see lombok.config
}
