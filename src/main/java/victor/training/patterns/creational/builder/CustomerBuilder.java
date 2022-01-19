package victor.training.patterns.creational.builder;

public class CustomerBuilder {
   private Customer customer = new Customer();

   public Customer build() {
      return customer;
   }

   public CustomerBuilder withName(String name) {
      customer.setName(name);
      return this;
   }

   public CustomerBuilder withAddress(Address address) {
      customer.setAddress(address);
      return this;
   }
}