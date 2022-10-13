package victor.training.patterns.builder.immutable;

public class BuilderPlay {
   public static void main(String[] args) {
      SomeDto dto = new SomeDto();
      dto.city = "Barcelona";
      dto.street = "La Rambla";
      dto.number = "17";
      dto.apt = "15"; // optional field

      // ugly constructor
      Address address = new Address(dto.number, dto.street, dto.city, dto.apt);

      System.out.println("I live at " + address);

   }
}

class SomeDto {
   String street, number, city, apt;
}