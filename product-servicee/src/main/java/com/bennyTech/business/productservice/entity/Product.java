package com.bennyTech.business.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "o campo nome nao pode estar vazio")
    private String name;
    private String description;


    @Positive(message = "O stock deve ser maior que zero")
    private Double stock;
    private Double price;
    private String status;


    @Column(name= "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull(message = "A categoria nao pode ser nula")
    @ManyToOne(fetch =FetchType.LAZY) // so carregara no momento que se requere
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}
