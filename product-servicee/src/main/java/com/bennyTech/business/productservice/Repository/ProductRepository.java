package com.bennyTech.business.productservice.Repository;

import com.bennyTech.business.productservice.entity.Category;
import com.bennyTech.business.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //permite encontrar um produto por categoria
    public List<Product> findByCategory(Category category);
}
