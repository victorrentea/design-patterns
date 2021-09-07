package victor.training.patterns.structural.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepo;

@Slf4j
@RequiredArgsConstructor
@Service // DOmain Service
public class RegisterCustomerService { //Customer Entity has 60 fields
   private final CustomerRepo customerRepo;
   private final CheckCustomerIsNewService checkCustomerIsNewService;

   public void registerCustomer(Customer customer) {

      // REST calls 10ms 0.01 cents
      checkCustomerIsNewService.checkUnique(customer);

      int discountPercentage = customer.getDiscountPercentage();

      System.out.println("Biz Logic with discount " + discountPercentage);
      // Heavy business logic
      // Heavy business logic
      customerRepo.save(customer);
      // Heavy business logic
   }

}


