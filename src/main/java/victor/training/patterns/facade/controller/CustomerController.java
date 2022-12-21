package victor.training.patterns.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import victor.training.patterns.facade.facade.CustomerFacade;
import victor.training.patterns.facade.facade.dto.CustomerDto;

@RestController
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerFacade customerFacade;

   @GetMapping("{customerId}")
   public CustomerDto findById(@PathVariable long customerId) {
      return customerFacade.findById(customerId);
   }

   @PostMapping
   public void register(@RequestBody CustomerDto customerDto) {
      customerFacade.register2(customerDto);
   }
}
