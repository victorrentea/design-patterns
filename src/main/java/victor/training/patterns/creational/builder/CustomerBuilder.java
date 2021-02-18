package victor.training.patterns.creational.builder;

import java.util.Arrays;

public class CustomerBuilder {
   private final Customer customer = new Customer();

   public CustomerBuilder withName(String name) {
      customer.setName(name);
      return this;
   }

   public Customer build() {
      return customer;
   }

   public CustomerBuilder withLabels(String... labelsArr) {
      customer.getLabels().addAll(Arrays.asList(labelsArr));
      return this;
   }

   public CustomerBuilder withAddress(Address address) {
      customer.setAddress(address);
      return this;
   }

   public Customer persist() {
      // hocus pocus de pe thread ia EntityManager.persist
      return build();
   }
}