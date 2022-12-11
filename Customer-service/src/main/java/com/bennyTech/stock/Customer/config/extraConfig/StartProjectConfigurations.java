package com.bennyTech.stock.Customer.config.extraConfig;

import com.bennyTech.stock.Customer.entity.Region;
import com.bennyTech.stock.Customer.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class StartProjectConfigurations implements CommandLineRunner {
    @Autowired
    private RegionRepository regionRepository;
    @Override
    public void run(String... args){
        Region region = new Region(1L,"Africa");
        Region region1 = new Region (2L,"Europe");
        Region region2 = new Region(3L, "America");
        Region region3 = new Region (4L, "Asia");
        regionRepository.saveAll(Arrays.asList(region,region1,region2,region3));
    }
}
