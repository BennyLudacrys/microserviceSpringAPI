package com.bennyTech.stock.Customer.controller;


import com.bennyTech.stock.Customer.entity.Customer;
import com.bennyTech.stock.Customer.entity.Region;
import com.bennyTech.stock.Customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCostumer(@RequestParam(name = "regionId", required = true) Long regionId){
        List<Customer> customers  = new ArrayList<>();
        if(null == regionId){
            customers = customerService.findCustomerAll();
            if (customers.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            Region region = new Region();
            region.setId(regionId);
            customers = customerService.findCustomersByRegion(region);
            if (null == customers){
                log.error("cliente com esse id {} nao econtrado.", regionId);
                return ResponseEntity.notFound().build();

            }
        }
           return ResponseEntity.ok(customers);
    }


}
