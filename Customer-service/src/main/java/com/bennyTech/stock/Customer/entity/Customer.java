package com.bennyTech.stock.Customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="tbl_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "este campo numeroID nao pode estar vazio")
    @Size(min = 9, max = 14, message = "este campo nao pode ter menos de 9 e nem mais de 8")
    private String numberID;
    @NotEmpty(message = "este campo nome nao pode estar vazio")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotEmpty(message = "este campo apelido nao pode estar vazio")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotEmpty(message = "este campo email nao pode estar vazio")
    @Email(message = "verifique o teu email, e corrija")
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "photo_url")
    private String photoUrl;


    @NotNull(message = "A regiao nao pode ser vazia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;
    private String state;



}
