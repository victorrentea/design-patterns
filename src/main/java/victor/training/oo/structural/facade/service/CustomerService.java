package victor.training.oo.structural.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.repo.CustomerRepository;

@Service
public class CustomerService {

   @Autowired
   private CustomerRepository customerRepo;

   public void registerCustomer(Customer customer) {
      // orice logica grea de business logic trebuie mutata intr-un Domain Service
      // Heavy logic
      // Heavy logic
      customerRepo.save(customer);
      // Heavy logic
   }
}
