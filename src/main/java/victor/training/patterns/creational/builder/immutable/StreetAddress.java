package victor.training.patterns.creational.builder.immutable;

import lombok.Getter;

@Getter
public class StreetAddress {
   private final String streetName;
   private final String streetNumber;

   public StreetAddress(String streetName, String streetNumber) {
      this.streetName = streetName;
      this.streetNumber = streetNumber;
   }
}
