package com.bennyTech.stock.Customer.controller;


import com.bennyTech.stock.Customer.entity.Customer;
import com.bennyTech.stock.Customer.entity.Region;
import com.bennyTech.stock.Customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(name = "regionId" , required = false) Long regionId ) {
        List<Customer> customers =  new ArrayList<>();
        if (null ==  regionId) {
            customers = customerService.findCustomerAll();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else{
            Region Region= new Region();
            Region.setId(regionId);
            customers = customerService.findCustomersByRegion(Region);
            if ( null == customers ) {
                log.error("Customers with Region id {} not found.", regionId);
                return  ResponseEntity.notFound().build();
            }
        }

        return  ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@Valid @PathVariable("id") long id) {
        log.info("Fetching Customer with id {}", id);
        Customer customer = customerService.getCustomer(id);
        if (  null == customer) {
            log.error("Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
        log.info("Creating Customer: {}", customer);
        Customer customer1 = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        log.info("Updating Customer with id {}", id);

        Customer currentCustomer = customerService.getCustomer(id);

        if ( null == currentCustomer ) {
            log.error("Unable to update. Customer with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer=customerService.updateCustomer(customer);
        return  ResponseEntity.ok(currentCustomer);
    }

    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        log.info("Fetching and Deleting customer with id {}", id);
        Customer customer= customerService.getCustomer(id);
        if(customer==null){
            log.error("Unable to delete. Customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        customer = customerService.deleteCustomer(customer);
        return ResponseEntity.ok(customer);
    }

}