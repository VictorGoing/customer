package com.kodilla.customer.service;

import com.kodilla.customer.domain.Customer;
import com.kodilla.customer.exception.CustomerNotFoundException;
import com.kodilla.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findCustomer(Long id){
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
