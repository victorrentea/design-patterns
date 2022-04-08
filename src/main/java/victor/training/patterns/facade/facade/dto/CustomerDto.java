package victor.training.patterns.facade.facade.dto;

public class CustomerDto {
   public Long id;
   public String name;
   public String email;
   public Long countryId;
   public String creationDateStr;

   public CustomerDto() {
   }

   public CustomerDto(String name, String email) {
      this.name = name;
      this.email = email;
   }

}
