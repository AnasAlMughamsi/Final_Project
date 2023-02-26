package com.example.finalproject.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     TODO: (check with Majd/Maha) I don't think I need username & password
        if both the customer/store class  have username & password
    */

    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "password is required")
    private String password;

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

    @NotEmpty(message = "gender is required")
    @Pattern(regexp = "male|female")
    @Column(columnDefinition = "varchar(10) not null check (gender= 'male' or gender= 'female')")
    private String gender;


//    @NotEmpty(message = "role is required")
    @Pattern(regexp = "customer|store")
    @Column(columnDefinition = "varchar(10) not null check (role= 'customer' or role= 'store')")
    @Nullable
    private String role;

    @Column(name = "dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateOfBirth;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Customer> customerList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Store> storeList;

}
