package victor.training.patterns.structural.facade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import victor.training.patterns.structural.facade.facade.CustomerFacade;
import victor.training.patterns.structural.facade.facade.dto.CustomerDto;

import javax.validation.Valid;

@RestController //faking it
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerFacade customerFacade;

   @GetMapping("{customerId}")
   public CustomerDto findById(@PathVariable long customerId) {
      return customerFacade.findById(customerId);
   }

   @PostMapping
   public void register(@Valid @RequestBody CustomerDto customerDto) {
      customerFacade.register(customerDto);
   }
}
