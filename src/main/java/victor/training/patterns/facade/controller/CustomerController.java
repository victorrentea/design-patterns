package victor.training.patterns.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.facade.CustomerFacade;
import victor.training.patterns.facade.facade.dto.CustomerDto;
import victor.training.patterns.facade.repo.CustomerRepo;

@RestController //faking it
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerFacade customerFacade;
   private final CustomerRepo customerRepo;

   @GetMapping("{customerId}")
   public CustomerDto findById(@PathVariable long customerId) {
      return customerFacade.findById(customerId);
   }


//   @GetMapping("{customerId}/2")
//   public Customer findByIdLeaking(@PathVariable long customerId) {
//      return customerRepo.findById(customerId);
//   }

   @PostMapping
   public void register(@RequestBody CustomerDto customerDto) {
      customerFacade.register(customerDto);
   }
}
