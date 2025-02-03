package org.web.library.service;

import org.web.library.dto.CustomerDto;
import org.web.library.model.Customer;

public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer update(CustomerDto customerDto);

    Customer changePass(CustomerDto customerDto);

    CustomerDto getCustomer(String username);
}
