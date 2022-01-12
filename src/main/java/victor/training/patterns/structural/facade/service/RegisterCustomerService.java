package victor.training.patterns.structural.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepo;

@Service
public class RegisterCustomerService {
   @Autowired
   CustomerRepo customerRepo;

   public void register(Customer customer) {
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

      int discountPercentage = customer.getDiscountPercentage();
      System.out.println("Biz Logic with discount " + discountPercentage);
      // Heavy business logic
      // Heavy business logic
      customerRepo.save(customer);
      // Heavy business logic
   }

}
