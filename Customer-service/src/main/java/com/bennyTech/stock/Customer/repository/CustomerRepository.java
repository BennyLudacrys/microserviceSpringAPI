package com.bennyTech.stock.Customer.repository;

import com.bennyTech.stock.Customer.entity.Customer;
import com.bennyTech.stock.Customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByNumberID(String numberId);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
}
