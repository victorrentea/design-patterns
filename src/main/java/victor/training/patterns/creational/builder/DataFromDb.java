package victor.training.patterns.creational.builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DataFromDb {

   //   static {
//      // whenever you have an instance "ready"
//      Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//      DataFromDb obj = new DataFromDb();
//      Set<ConstraintViolation<DataFromDb>> validate = validator.validate(obj);
//   }
   @NotNull
   private String name;
   @NotNull
   @Size(min = 6)
   private String phone;

   public String getName() {
      return name;
   }

   public DataFromDb setName(String name) {
      this.name = name;
      return this;
   }

   public String getPhone() {
      return phone;
   }

   public DataFromDb setPhone(String phone) {
      this.phone = phone;
      return this;
   }
}
