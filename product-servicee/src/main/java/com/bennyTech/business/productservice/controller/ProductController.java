package com.bennyTech.business.productservice.controller;

import com.bennyTech.business.productservice.entity.Category;
import com.bennyTech.business.productservice.entity.Product;
import com.bennyTech.business.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value= "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products = new ArrayList<>();

        if (null == categoryId){
            products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products= productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if (null==product){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
//        if (result.hasErrors()){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
//        }
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        Product product1 = productService.updateProduct(product);
        if(product1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product1);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);
        if(null== productDelete){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,@RequestParam(name = "quantity", required = true) Double quantity){
        Product product = productService.updateStock(id, quantity);
        if (product ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
