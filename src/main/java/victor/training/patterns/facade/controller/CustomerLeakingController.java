package victor.training.patterns.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.repo.CustomerRepo;

@RestController
@RequiredArgsConstructor
public class CustomerLeakingController {
   private final CustomerRepo customerRepo;

   @GetMapping("{customerId}/leak")
   public Customer findById(@PathVariable long customerId) {
      return customerRepo.findById(customerId);
   }

}
