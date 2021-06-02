package victor.training.patterns.creational.builder;

import static java.util.Arrays.asList;

public class CustomerBuilder {
   private final Customer customerUnderConstruction = new Customer();

   public CustomerBuilder withName(String name) {
      customerUnderConstruction.setName(name);
      return this;
   }

   public CustomerBuilder withAddress(Address address) {
      customerUnderConstruction.setAddress(address);
      return this;
   }

   public Customer build() {
      return customerUnderConstruction;
   }

   public CustomerBuilder addLabel(String... label) {
      customerUnderConstruction.getLabels().addAll(asList(label));
      return this;
   }
}