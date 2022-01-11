package victor.training.patterns.creational.builder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CustomerBuilder {
   private Long id;
   private String name;
   private String phone;
   private List<String> labels;
   private Address address;
   private Date createDate;

   public CustomerBuilder withName(String name) {
      this.name = name;
      return this;
   }

   public Customer build() {
      return new Customer(id, name, phone, labels, address, createDate);
   }

   public CustomerBuilder withLabels(String... labels) {
      this.labels = Arrays.asList(labels);
      return this;
   }

   public CustomerBuilder withAddress(Address address) {
      this.address = address;
      return this;
   }

   public CustomerBuilder withAddress(AddressBuilder address) {
      this.address = address.build();
      return this;
   }
}