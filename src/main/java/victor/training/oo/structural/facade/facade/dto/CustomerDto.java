package victor.training.oo.structural.facade.facade.dto;

import victor.training.oo.structural.facade.entity.Customer;

public class CustomerDto {
    public Long id;
    public String name;
    public String email;
    public Long countryId;

    public CustomerDto() {
    }

//    public Customer toEntity() {
//        Customer customer = new Customer();
//        customer.setEmail(email);
//        customer.setName(name);
//        customer.setSite(siteRepo.getReference(countryId));
//        return customer;
//    }

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public CustomerDto(Customer customer) {
        name = customer.getName();
        email = customer.getEmail();
        id = customer.getId();
    }
}
