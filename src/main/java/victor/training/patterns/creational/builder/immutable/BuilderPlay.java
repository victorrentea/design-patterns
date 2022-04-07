package victor.training.patterns.creational.builder.immutable;

public class BuilderPlay {
   public static void main(String[] args) {
      SomeDto dto = new SomeDto();
      dto.city = "Barcelona";
      dto.street = "La Rambla";
      dto.number = "17";
      dto.apt = "15"; // opt
      uglyConstructor(dto);
   }
   public static void uglyConstructor(SomeDto dto) {

      Address address = new Address(
          dto.street,
          dto.number,
          dto.city); // mai bine > compilatorul se asigura ca ai dat tot

      Address addressDinPlomba = address.withAptNumber("2");

      new AddressBuilder()
          .withStreetNumber(dto.number)
          .withCity(dto.city)
          .withStreetName(dto.street)
          .withAptNumber(dto.apt)
          .build();

//      new Address.AddressBuilder().
//

      Address a = Address.builder()
//          .streetNumber(dto.number)
//          .city(dto.city)
//          .streetName(dto.street)
//          .aptNumber(dto.apt)
          .build();


      System.out.println("I live at " + address);

   }

}

class SomeDto {
   public String zipCode;
   public String phone;
   public String country;
   String street, number, city, apt;
}