package victor.training.patterns.builder.immutable;

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
      Address address = new Address(dto.number, dto.street, dto.city, dto.apt,
              "11", "a","b","c","d");
      Address copy = address.withFloorNumber("different");
      Address.builder()
                      .floorNumber("11")
                      .floorNumber("11")
                      .floorNumber("11")
                      .floorNumber("11")
                      .floorNumber("11")
                              .build();
      System.out.println("I live at " + address);

   }
}

class SomeDto {
   String street, number, city, apt;
}