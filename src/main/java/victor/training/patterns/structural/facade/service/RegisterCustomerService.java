package victor.training.patterns.structural.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepo;

//class SearchCustmerService
//class FindCurrentUserAtLogin {} 5 LOC
//class CustomerService {}// 10K lines of code
@Service
public class RegisterCustomerService { // 50-250
   @Autowired
   private CustomerRepo customerRepo;
//   @Autowired
//   private RegisterBusinessExtraCustomer registerBusinessExtraCustomer;

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
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      customerRepo.save(customer);
      // Heavy business logic
   }
}
