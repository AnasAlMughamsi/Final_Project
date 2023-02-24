package com.example.finalproject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "user")
@AllArgsConstructor @NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

    @NotEmpty(message = "role is required")
    @Pattern(regexp = "customer|store|admin")
    @Column(columnDefinition = "varchar(10) not null check (role= 'customer' or role= 'store' or role= 'admin')")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Customer> customerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Store> storeList;

}
