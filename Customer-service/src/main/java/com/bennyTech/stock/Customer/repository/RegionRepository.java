package com.bennyTech.stock.Customer.repository;

import com.bennyTech.stock.Customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository <Region, Long> {
}
