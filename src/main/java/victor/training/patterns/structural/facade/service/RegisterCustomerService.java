package victor.training.patterns.structural.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.facade.entity.Customer;
import victor.training.patterns.structural.facade.repo.CustomerRepo;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService {
   private final CustomerRepo customerRepo;

   //   public void registerCustomer(RegisterCustomerRequest customer) {
   public void registerCustomer(Customer customer) {
      // Heavy business logic
      // Heavy business logic
      extracted(customer);
   }

   private void extracted(Customer customer) {
      // Heavy business logic
      // Heavy business logic
      int discount = customer.computeDiscount();

      System.out.println(discount);

      // Heavy business logic
      customerRepo.save(customer);
      // Heavy business logic
   }

}

class RegisterCustomerRequest {
//   long long string
}
