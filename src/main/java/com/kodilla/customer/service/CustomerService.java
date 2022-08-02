package com.kodilla.customer.service;

import com.kodilla.customer.domain.Customer;
import com.kodilla.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
