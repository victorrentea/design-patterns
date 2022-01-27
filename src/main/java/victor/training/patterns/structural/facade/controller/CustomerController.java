package victor.training.patterns.structural.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import victor.training.patterns.structural.facade.facade.CustomerServiceImpl;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;

@RestController //faking it
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerServiceImpl customerFacade;

   @GetMapping("{customerId}")
   public CustomerDto findById(@PathVariable long customerId) {
      return customerFacade.findById(customerId);
   }

   @PostMapping
   public void register(@RequestBody CustomerDto customerDto) {
      customerFacade.register(customerDto);
   }
}
