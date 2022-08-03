package com.kodilla.customer.controller;


import com.kodilla.customer.controller.response.GetCustomerResponse;
import com.kodilla.customer.domain.Customer;
import com.kodilla.customer.dto.CustomerDto;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping(value = "{idCustomer}")
    public ResponseEntity<GetCustomerResponse> getCustomerById(@PathVariable Long idCustomer){
        Customer customer = customerService.getCustomerById(idCustomer);
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);
        GetCustomerResponse response = new GetCustomerResponse(customerDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewCustomer(@RequestBody CustomerDto customerDto){
        Customer customer = customerMapper.mapNewCustomer(customerDto);
        customerService.saveCustomer(customer);
        return ResponseEntity.ok().build();
    }

}
