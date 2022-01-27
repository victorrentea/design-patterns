package victor.training.patterns.structural.facade.service;

import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepo;

@Service
public class RegisterCustomerDomainService {
   public final CustomerRepo customerRepo;

   public RegisterCustomerDomainService(CustomerRepo customerRepo) {
      this.customerRepo = customerRepo;
   }

   public void registerCustomer(Customer customer) {
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic
      // Heavy business logic

      int discountPercentage = 3;
      if (customer.isGoldMember()) {
         discountPercentage += 1;
      }
      System.out.println("Biz Logic with discount " + discountPercentage);
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