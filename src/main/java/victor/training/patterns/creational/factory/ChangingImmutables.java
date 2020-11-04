package victor.training.patterns.creational.factory;

import victor.training.patterns.creational.factory.FullName.Prefix;

public class ChangingImmutables {
   public void method() {

      FullName fullName = new FullName(Prefix.MR, "Maria", "Rentea");

//      fullName.withLastName("TheOtherGuy");
      fullName = fullName.withLastName("TheOtherGuy");
      fullName = fullName.withPrefix(Prefix.MRS);

      System.out.println(fullName.toFullName());
   }

}

class FullName {


   enum Prefix {
      MR, MS, MRS;

   }
   private final Prefix prefix;
   private final String firstName;
   private final String lastName;
   public FullName(Prefix prefix, String firstName, String lastName) {
      this.prefix = prefix;
      this.firstName = firstName;
      this.lastName = lastName;
   }
   public FullName withLastName(String newLastName) {
      return new FullName(getPrefix(), getFirstName(), newLastName);
   }

   public FullName withPrefix(Prefix newPrefix) {
      return new FullName(newPrefix, firstName, lastName);
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

