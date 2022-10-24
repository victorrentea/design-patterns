package victor.training.patterns.builder.small;

public class BuilderPlay {
   public static void main(String[] args) {
      // ugly canonical constructor
      Address address = new Address("91", "Dristorului", "Bucharest", "1", "a");

      // minimal constructor + wither chain
      address = new Address("91", "Dristorului", "Bucharest")
              .withAptNumber("1")
              .withFloorNumber("a");

      System.out.println("I live at " + address);

   }
}