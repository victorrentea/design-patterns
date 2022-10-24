package victor.training.patterns.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.repo.CustomerRepo;

@RequiredArgsConstructor
@Service
public class RegisterCustomerService {
    // cohesive name; people will NOT leave their shit in here by accident
    private final CustomerRepo customerRepo;
    public void registerCustomer(Customer customer) {
        // Heavy business logic using Customer ENTITY!!
        // Heavy business logic
        // Heavy business logic


        // assume this is useful in many places:
        // 1) Util/CustomerHelper/CustomerService
        // 2) Customer @Entity
        int discountPercentage = customer.getDiscountPercentage();
        System.out.println("Biz Logic with discount " + discountPercentage);
        // Heavy business logic
        // Heavy business logic
        customerRepo.save(customer);
        // Heavy business logic
    }

}
