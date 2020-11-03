package victor.training.patterns.creational.factory;

import victor.training.patterns.creational.factory.FullName.Prefix;

public class ChangingImmutables {
   public void method() {
      FullName fullName = new FullName(Prefix.MR, "Maria", "Rentea");
      System.out.println(fullName.toFullName());
   }
}

class FullName {
   enum Prefix {
      MR, MS, MRS
   }
   private final Prefix prefix;
   private final String firstName;
   private final String lastName;

   public FullName(Prefix prefix, String firstName, String lastName) {
      this.prefix = prefix;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public Prefix getPrefix() {
      return prefix;
   }

   public String toFullName() {
      return prefix + " " + firstName + " " + lastName.toUpperCase();

   }
}

