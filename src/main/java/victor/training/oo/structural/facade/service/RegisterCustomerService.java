package victor.training.oo.structural.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.repo.CustomerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RegisterCustomerService {


   public static void main(String[] args) {

      List<String> strings = new ArrayList<>();

      strings.add("a");
      strings.add("b");

      List<String> strings2 = Collections.unmodifiableList(strings);
      strings2.add("c");


   }
   @Autowired
   private CustomerRepository customerRepo;

   public void registerCustomer(Customer customer) {
      // orice logica grea de business logic trebuie mutata intr-un Domain Service
      // Heavy logic
      // Heavy logic


      customerRepo.save(customer);
      // Heavy logic
   }
}
