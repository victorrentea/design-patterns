package victor.training.patterns.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.repo.CustomerRepo;

@Service
@RequiredArgsConstructor // generates the ctor you don;t see
@Slf4j
public class CustomerService {
    private final CustomerRepo customerRepo;
    public void registerCustomer(Customer customer) {
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
