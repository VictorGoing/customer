package com.kodilla.customer.mapper;

import com.kodilla.customer.domain.Customer;
import com.kodilla.customer.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public CustomerDto mapToCustomerDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName()
                );
    }

    public Customer mapToCustomer(CustomerDto customerDto){
        return new Customer(
                customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getLastName()
        );
    }

    public Customer mapNewCustomer(CustomerDto customerDto){
        return new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName()
        );
    }
}
