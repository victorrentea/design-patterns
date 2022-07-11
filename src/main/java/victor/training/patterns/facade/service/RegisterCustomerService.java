package victor.training.patterns.facade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.facade.repo.CustomerRepo;

@RequiredArgsConstructor
@Service
public class RegisterCustomerService {
    private final CustomerRepo customerRepo;

    public void registerCustomer(Customer customer) {
        // pentru ca esti in sfantul Domain, nu mai ai voie sa
        // vezi Dto-uri; nici ale tale, nici ale altora.
        // doar obiectele TALE, create pentru a te AJUTA sa implem logica ta.

        // Heavy business logic
        // Heavy business logic  5 ifuri
        // Heavy business logic for

        int discountPercentage = customer.getDiscountPercentage();
        System.out.println("Biz Logic with discount " + discountPercentage);
        // Heavy business logic
        // Heavy business logic
        customerRepo.save(customer);
        // Heavy business logic
    }
}
