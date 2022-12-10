package com.bennyTech.business.productservice.service;

import com.bennyTech.business.productservice.entity.Category;
import com.bennyTech.business.productservice.entity.Product;

import java.util.List;

public interface ProductService {//implementacao dos metodos que serao usados na implementacao
    public List<Product> listAllProduct();
    public Product getProduct(Long id);


    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Double quantity);
}
