package victor.training.patterns.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepository;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService {
   private final CustomerRepository customerRepo;

   public void registerCustomer(Customer customer) {
      // LOGICA DE BIZ
      // Heavy logic
      // Heavy logic
      // LOGICA DE BIZ
      // Heavy logic
      // Heavy logic
      // LOGICA DE BIZ
      // Heavy logic
      // Heavy logic
      customerRepo.save(customer);
      // Heavy logic
   }
}
