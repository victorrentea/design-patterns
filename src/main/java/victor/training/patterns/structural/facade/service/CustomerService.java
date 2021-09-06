package victor.training.patterns.structural.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepo;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {
   private final CustomerRepo customerRepo;

   public void registerCustomer(Customer customer) {

      // REST calls 10ms 0.01 cents
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
