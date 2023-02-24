package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "store")
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "storeName is required")
    private String storeName;

    @NotEmpty(message = "city is required")
    private String city;
    @NotEmpty(message = "district is required")
    private String district;

    @NotEmpty(message = "street is required")
    private String street;
    @NotEmpty(message = "phone number is required")
    private String phoneNumber;

    @NotEmpty(message = "email number is required")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotEmpty(message = "company register certification number is required")
    private String companyRegisterUrl;
    private boolean isActive; // by admin
    private String commercialLicense;

    //  Relationships

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private List<Customer> customers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private List<Product> productList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;



}
