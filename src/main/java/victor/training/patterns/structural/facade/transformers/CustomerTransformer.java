package victor.training.patterns.structural.facade.transformers;

import org.springframework.stereotype.Component;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;

@Component
public class CustomerTransformer {
   public Customer transform(CustomerDto dto) {
      Customer customer = new Customer();
      customer.setEmail(dto.email);
      customer.setName(dto.name);

      return customer;
   }
}
