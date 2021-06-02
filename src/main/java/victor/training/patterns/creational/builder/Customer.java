package victor.training.patterns.creational.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.requireNonNull;


public class Customer {
   private Long id;
   private String name;
   private String phone;
   private List<String> labels = new ArrayList<>();
   private Address address;
   private Date createDate;

//   private DataFromDb someData ;
//   private DataFromApis otherData;

   // programatic self-validation
//   private Customer() {} // invoked reflectively by the framework deserializing the instance from the wire
//   public Customer(String name/*,String name1,String nam2,String nam4,String nam5,String name6,String name8*/) {
//      setName(name);
//   }

   public Long getId() {
      return id;
   }

   public Customer setId(Long id) {
      this.id = id;
      return this;
   }

   public String getName() {
      return name;
   }

   public Customer setName(String name) {
//      if (name == null) {
//         throw new IllegalArgumentException();
//      }
      this.name = requireNonNull(name);
      return this;
   }

   public String getPhone() {
      return phone;
   }

   public Customer setPhone(String phone) {
      this.phone = phone;
      return this;
   }

   public List<String> getLabels() {
      return labels;
   }

   public Customer setLabels(List<String> labels) {
      this.labels = labels;
      return this;
   }

   public Date getCreateDate() {
      return createDate;
   }

   public Customer setCreateDate(Date createDate) {
      this.createDate = createDate;
      return this;
   }

   public Address getAddress() {
      return address;
   }

   public Customer setAddress(Address address) {
      this.address = address;
      return this;
   }
}
