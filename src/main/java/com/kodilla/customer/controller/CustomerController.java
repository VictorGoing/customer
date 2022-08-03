package com.kodilla.customer.controller;


import com.kodilla.customer.controller.response.GetCustomerProductsResponse;
import com.kodilla.customer.controller.response.GetCustomerResponse;
import com.kodilla.customer.domain.Customer;
import com.kodilla.customer.dto.AccountDto;
import com.kodilla.customer.dto.CustomerDto;
import com.kodilla.customer.exception.CustomerNotFoundException;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.service.CustomerService;
import com.kodilla.customer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RefreshScope
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ProductService productService;
    private final CustomerMapper customerMapper;

    @GetMapping(value = "{idCustomer}")
    public ResponseEntity<GetCustomerResponse> getCustomerById(@PathVariable Long idCustomer){
        Customer customer = customerService.findCustomer(idCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);
        GetCustomerResponse response = new GetCustomerResponse(customerDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}/products")
    public GetCustomerProductsResponse getCustomerProducts(@PathVariable Long customerId){
        Customer customer = customerService.findCustomer(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);
        List<AccountDto> customerAccounts = productService.findCustomerAccounts(customerId);

        return GetCustomerProductsResponse.builder()
                .customerId(customerDto.getId())
                .fullName(customerDto.getFirstName() + " " + customerDto.getLastName())
                .accounts(customerAccounts)
                .build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewCustomer(@RequestBody CustomerDto customerDto){
        Customer customer = customerMapper.mapNewCustomer(customerDto);
        customerService.saveCustomer(customer);
        return ResponseEntity.ok().build();
    }

}
