package com.bennyTech.store.shopping.entity;

import com.bennyTech.store.shopping.model.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data
@Table(name = "tbl_invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "a quantidade deve ser maior que zero")
    private Double quantity;
    private Double price;
    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subtotal;
    @Transient
    private Product product;

    public Double getSubtotal(){
        if (price>0 && quantity>0){
            return quantity*price;
        }else{
            return (double) 0;

        }
    }
    public InvoiceItem(){
        quantity= (double) 0;
        price = (double) 0;
    }
}
