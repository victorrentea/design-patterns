package victor.training.oo.structural.facade.facade.dto;

import victor.training.oo.structural.facade.entity.Customer;

public class CustomerDto {
    public Long id;
	public String name;
	public String email;
    public Long countryId;
    public String creationDateStr;

    public CustomerDto() {}

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
//       File file;
//       OutputStream os = new FileOutputStream(file);
//       OutputStream bufferedOutputStream = new BufferedOutputStream(os);
    }

   public CustomerDto(Customer entity) {
       name = entity.getName();
       email = entity.getEmail();
   }

}
