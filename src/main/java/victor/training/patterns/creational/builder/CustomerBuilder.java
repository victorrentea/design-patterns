//package victor.training.patterns.creational.builder;
//
//import java.util.Arrays;
//
//public class CustomerBuilder {
//   private final Customer customer = new Customer(id, name, phone, labels, address, createDate);
//
//   public CustomerBuilder withName(String name) {
//      customer.setName(name);
//      return this;
//   }
//
//   public Customer build() {
//      return customer;
//   }
//
//   public CustomerBuilder withLabels(String... labels) {
//      customer.setLabels(Arrays.asList(labels));
//      return this;
//   }
//
//   public CustomerBuilder withAddress(Address address) {
//      customer.setAddress(address);
//      return this;
//   }
//}