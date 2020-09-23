package victor.training.oo.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.repo.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

   private final CustomerRepository customerRepo;

   public void registerCustomer(Customer customer) {
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      customerRepo.save(customer);
      // Heavy logic
   }
}
